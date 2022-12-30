package com.ikjunweb.controller;

import com.ikjunweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

@Controller
public class FormController {

    private final UserService userService;

    @Autowired
    public FormController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/ikjun/registerForm")
    public String registerForm() {
        return "registerForm";
    }

    @PostMapping("/ikjun/registerForm/username")
    public HashMap<String, Object> usernameOverlap(@RequestBody String username) {
        return userService.usernameOverlap(username);
    }

    @GetMapping("/ikjun/loginForm")
    public String loginForm() {
        return "loginForm";
    }
}
