package com.example.socialnetwork.controllers.apis;

import com.example.socialnetwork.DTOs.StringDTO;
import com.example.socialnetwork.models.Request;
import com.example.socialnetwork.models.User;
import com.example.socialnetwork.models.enums.RequestType;
import com.example.socialnetwork.models.enums.UserStatus;
import com.example.socialnetwork.services.request.IRequestService;
import com.example.socialnetwork.services.user.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
@Slf4j
public class ApiRequestController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IRequestService requestService;

    @PostMapping(value = "/forgot")
    public ResponseEntity<?> sendCodeForgotToEmail(@RequestBody StringDTO email) {
        Optional<User> user = userService.findByEmail(email.getEmail());
        if (user.isEmpty()) {
            return ResponseEntity.status(404).body("User don't exist");
        }
        if (user.get().getStatus() == UserStatus.LOCKED) {
            return ResponseEntity.status(401).body("User is blocked");
        }
        Optional<Request> request = requestService.findByEmailRequest(email.getEmail());
        if (request.isPresent()) {
            request.get().setRequestType(RequestType.FORGOT);
            Optional<Request> requestUpdate = requestService.save(request.get());
            boolean sendCodeSuccess = requestService.sendCodeToEmail(requestUpdate.get().getEmailRequest(), "CONFIRM FORGOT", requestUpdate.get().getRequestCode());
            if (!sendCodeSuccess) {
                log.error("[SEND CODE TO EMAIL] - FAILED");
                return ResponseEntity.status(500).body("Send code to email failed");
            }
            return ResponseEntity.ok("Send code to email success");
        }
        request = Optional.of(new Request(email.getEmail(), RequestType.FORGOT));
        try {
            Optional<Request> requestAdd = requestService.save(request.get());
            if (requestAdd.isEmpty()) {
                return ResponseEntity.status(500).body("Server error");
            }
            boolean sendCodeSuccess = requestService.sendCodeToEmail(requestAdd.get().getEmailRequest(), "CONFIRM FORGOT", requestAdd.get().getRequestCode());
            if (!sendCodeSuccess) {
                log.error("[SEND CODE TO EMAIL] - FAILED");
                return ResponseEntity.status(500).body("Send code to email failed");
            }
            return ResponseEntity.ok("Send code to email success");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Server error");
        }
    }

    @PostMapping(value = "/refresh-code")
    public ResponseEntity<?> sendRefreshCodeToEmail(@RequestBody StringDTO email) {
        Optional<Request> request = requestService.findByEmailRequest(email.getEmail());
        if (request.isPresent()) {
            Optional<Request> requestUpdate = requestService.save(request.get());
            boolean sendCodeSuccess = requestService.sendCodeToEmail(requestUpdate.get().getEmailRequest(), "CONFIRM FORGOT", requestUpdate.get().getRequestCode());
            if (!sendCodeSuccess) {
                log.error("[SEND CODE TO EMAIL] - FAILED");
                return ResponseEntity.status(500).body("Send code to email failed");
            }
            return ResponseEntity.ok("Send code to email success");
        }
        request = Optional.of(new Request(email.getEmail(), RequestType.FORGOT));
        try {
            Optional<Request> requestAdd = requestService.save(request.get());
            if (requestAdd.isEmpty()) {
                return ResponseEntity.status(500).body("Server error");
            }
            boolean sendCodeSuccess = requestService.sendCodeToEmail(requestAdd.get().getEmailRequest(), "CONFIRM FORGOT", requestAdd.get().getRequestCode());
            if (!sendCodeSuccess) {
                log.error("[SEND CODE TO EMAIL] - FAILED");
                return ResponseEntity.status(500).body("Send code to email failed");
            }
            return ResponseEntity.ok("Send code to email success");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Server error");
        }
    }
}
