package com.example.socialnetwork.controllers;

import com.example.socialnetwork.DTOs.UserResponseDTO;
import com.example.socialnetwork.models.*;
import com.example.socialnetwork.services.like.ILikeService;
import com.example.socialnetwork.services.post.IPostService;
import com.example.socialnetwork.services.user.IUserService;
import com.example.socialnetwork.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ApplicationController {

    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping(value = "/error")
    public String showError() {
        return "errors/server-error";
    }

    @GetMapping(value = "/404")
    public String showError404() {
        return "errors/404";
    }
}
