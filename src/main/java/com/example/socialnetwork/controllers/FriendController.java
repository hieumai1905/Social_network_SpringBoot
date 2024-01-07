package com.example.socialnetwork.controllers;

import com.example.socialnetwork.models.User;
import com.example.socialnetwork.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FriendController {

    @Autowired
    private IUserService userService;

    private static final String userId = "50d5a805-81a1-43fa-a2cd-d86a615e933a";

    @GetMapping(value = "/friends")
    public ModelAndView showFriends() {
        ModelAndView modelAndView = new ModelAndView("friend");
        List<User> users = this.userService.findAllByRelationTypeOfUser("FRIEND", userId);
        modelAndView.addObject("friends", users);
        return modelAndView;
    }

}
