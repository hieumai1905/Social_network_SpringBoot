package com.example.socialnetwork.controllers;

import com.example.socialnetwork.DTOs.UserLoginDTO;
import com.example.socialnetwork.DTOs.UserPasswordUpdateDTO;
import com.example.socialnetwork.DTOs.UserRegisterDTO;
import com.example.socialnetwork.models.Request;
import com.example.socialnetwork.models.User;
import com.example.socialnetwork.models.enums.RequestType;
import com.example.socialnetwork.models.enums.UserStatus;
import com.example.socialnetwork.services.request.IRequestService;
import com.example.socialnetwork.services.user.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@Slf4j
public class AccountController {

    @Autowired
    private HttpSession session;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRequestService requestService;

    @GetMapping(value = "/login")
    public String showFormLogin() {
        return "login";
    }

    @GetMapping(value = "/register")
    public String shorFormRegister() {
        return "register";
    }

    @GetMapping(value = "/lock")
    public String showViewLock() {
        return "lock";
    }

    @GetMapping(value = "/forgot")
    public String showFormForgotPassword() {
        return "forgot-password";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute UserRegisterDTO userRegisterDTO, Model model) {
        Optional<User> userExist = userService.findByEmail(userRegisterDTO.getEmail());
        if (userExist.isPresent()) {
            String message = "";
            UserStatus userStatus = userExist.get().getStatus();
            switch (userStatus) {
                case ACTIVE:
                    message = "Email is already exist";
                    model.addAttribute("message", message);
                    return "register";
                case INACTIVE:
                    session.setAttribute(userRegisterDTO.getEmail(), UserStatus.INACTIVE);
                    model.addAttribute("email", userRegisterDTO.getEmail());
                    return "confirm-code";
            }
        }

        log.info("[REGISTER] - START - USER: {}", userRegisterDTO.toString());
        boolean registerSuccess = userService.registerUser(userRegisterDTO);
        if (registerSuccess) {
            log.info("[REGISTER] - DONE ==> USER: {}", userRegisterDTO);
            Request request = new Request(userRegisterDTO.getEmail(), RequestType.REGISTER);
            log.info("[REQUEST CODE] - START - REQUEST: {}", request.toString());
            try {
                Optional<Request> requestAdd = requestService.save(request);
                log.info("[REQUEST CODE] - DONE ==> REQUEST: {}", request.toString());
                if (requestAdd.isPresent()) {
                    log.info("[SEND CODE TO EMAIL] - START - CODE: {}", requestAdd.get().getRequestCode());
                    boolean sendCodeSuccess = requestService.sendCodeToEmail(requestAdd.get().getEmailRequest(), "CONFIRM REGISTER", requestAdd.get().getRequestCode());
                    if (!sendCodeSuccess) {
                        log.error("[SEND CODE TO EMAIL] - FAILED");
                        return "redirect:/error";
                    }
                    log.info("[SEND CODE TO EMAIL] - DONE ==> CODE: {}", requestAdd.get().getRequestCode());
                    session.setAttribute(userRegisterDTO.getEmail(), UserStatus.INACTIVE);
                    model.addAttribute("email", userRegisterDTO.getEmail());
                    return "confirm-code";
                }
            } catch (Exception e) {
                log.error("[REQUEST CODE] - FAILED ==> REQUEST: {}", request);
            }
        }
        log.error("[REGISTER] - FAILED ==> USER: {}", userRegisterDTO);
        return "redirect:/confirm";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute UserLoginDTO userLogin, Model model) {
        Optional<User> user = userService.loginUser(userLogin);
        if (user.isPresent()) {
            switch (user.get().getStatus()) {
                case ACTIVE:
                    return "redirect:/index";
                case INACTIVE:
                    Optional<Request> request = requestService.findByEmailRequest(userLogin.getEmail());
                    if (request.isPresent()) {
                        request.get().setRequestType(RequestType.REGISTER);
                        Optional<Request> requestUpdate = requestService.save(request.get());
//                        boolean sendCodeSuccess = requestService.sendCodeToEmail(userLogin.getEmail(), "CONFIRM REGISTER", requestUpdate.get().getRequestCode());
//                        if (!sendCodeSuccess) {
//                            return "redirect:/error";
//                        }
                        session.setAttribute(userLogin.getEmail(), UserStatus.INACTIVE);
                        model.addAttribute("email", userLogin.getEmail());
                        model.addAttribute("message", "Account is not active, please confirm code");
                        return "confirm-code";
                    }
                    request = Optional.of(new Request(userLogin.getEmail(), RequestType.REGISTER));
                    Optional<Request> requestAdd = requestService.save(request.get());
                    if (requestAdd.isEmpty()) {
                        return "redirect:/error";
                    }
//                    boolean sendCodeSuccess = requestService.sendCodeToEmail(userLogin.getEmail(), "CONFIRM REGISTER", requestUpdate.get().getRequestCode());
//                    if (!sendCodeSuccess) {
//                        return "redirect:/error";
//                    }
                    session.setAttribute(userLogin.getEmail(), UserStatus.INACTIVE);
                    model.addAttribute("email", userLogin.getEmail());
                    model.addAttribute("message", "Account is not active, please confirm code");
                    return "confirm-code";
                case LOCKED:
                    return "lock";
                default:
                    return "redirect:/404";
            }
        }
        model.addAttribute("message", "Email or password is incorrect");
        return "login";
    }

    @PostMapping(value = "/register/confirm-code")
    public String confirmCodeRegister(@RequestParam String email, @RequestParam String code, Model model) {
        UserStatus userStatus = (UserStatus) session.getAttribute(email);
        Optional<User> user = userService.findByEmail(email);
        Optional<Request> request = requestService.findByEmailRequest(email);
        if (userStatus == null || userStatus != UserStatus.INACTIVE || user.isEmpty() || user.get().getStatus() == UserStatus.LOCKED || request.isEmpty()) {
            return "errors/404";
        }
        if (request.get().getRequestType() != RequestType.REGISTER) {
            model.addAttribute("email", email);
            model.addAttribute("message", "Request don't exist");
            return "confirm-code";
        }
        long timeOut = System.currentTimeMillis() - request.get().getRequestAt().getTime();
        if (!request.get().getRequestCode().equals(code)) {
            model.addAttribute("email", email);
            model.addAttribute("message", "Code is incorrect");
            return "confirm-code";
        }
        if (timeOut > 60000) {
            model.addAttribute("email", email);
            model.addAttribute("reset", "reset");
            model.addAttribute("message", "Request is expired");
            return "confirm-code";
        }
        user.get().setStatus(UserStatus.ACTIVE);
        Optional<User> userUpdate = userService.save(user.get());
        if (userUpdate.isEmpty()) {
            return "redirect:/error";
        }
        requestService.delete(request.get());
        return "redirect:/login";
    }

    @PostMapping(value = "/forgot/confirm-code")
    public String confirmCodeForgotAccount(@RequestParam String email, @RequestParam String code, Model model) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isEmpty()) {
            model.addAttribute("message", "User don't exist");
            return "forgot-password";
        }
        if (user.get().getStatus() == UserStatus.LOCKED) {
            model.addAttribute("message", "User is blocked");
            return "forgot-password";
        }
        Optional<Request> request = requestService.findByEmailRequest(email);
        if (request.isEmpty()) {
            model.addAttribute("message", "Request don't exist");
            return "forgot-password";
        } else {
            if (request.get().getRequestType() != RequestType.FORGOT) {
                model.addAttribute("message", "Request don't exist");
                return "forgot-password";
            }
            long timeOut = System.currentTimeMillis() - request.get().getRequestAt().getTime();
            if (timeOut > 60000) {
                model.addAttribute("message", "Request is expired");
                return "forgot-password";
            }
            if (!request.get().getRequestCode().equals(code)) {
                model.addAttribute("message", "Code is incorrect");
                return "forgot-password";
            }
        }
        model.addAttribute("email_update", email);
        session.setAttribute(email, "UPDATE_PASSWORD");
        return "update-password";
    }

    @PostMapping(value = "/forgot/update-password")
    public String updatePasswordForgotAccount(@ModelAttribute UserPasswordUpdateDTO userPasswordUpdateDTO, Model model) {
        String email = userPasswordUpdateDTO.getEmail();
        Optional<Request> request = requestService.findByEmailRequest(email);
        if (request.isEmpty()) {
            model.addAttribute("message", "Request don't exist");
            return "forgot-password";
        }
        long timeOut = System.currentTimeMillis() - request.get().getRequestAt().getTime();
        if (timeOut > 60000) {
            model.addAttribute("message", "Request is expired");
            return "forgot-password";
        }
        Optional<User> user = userService.findByEmail(email);
        if (user.isEmpty()) {
            model.addAttribute("message", "User don't exist");
            return "forgot-password";
        }
        if (user.get().getStatus() == UserStatus.LOCKED) {
            model.addAttribute("message", "User is blocked");
            return "forgot-password";
        }
        if (!session.getAttribute(email).equals("UPDATE_PASSWORD")) {
            return "redirect:/404";
        }
        if (!userPasswordUpdateDTO.getNewPassword().equals(userPasswordUpdateDTO.getConfirmPassword())) {
            model.addAttribute("message", "Password and confirm password is not match");
            return "update-password";
        }
        user.get().setPassword(userPasswordUpdateDTO.getNewPassword());
        Optional<User> userUpdate = userService.save(user.get());
        if (userUpdate.isEmpty()) {
            model.addAttribute("message", "Update password failed");
            return "update-password";
        }
        requestService.delete(request.get());
        requestService.clearRequestCodeExpired();
        session.removeAttribute(email);
        return "redirect:/login";
    }
}