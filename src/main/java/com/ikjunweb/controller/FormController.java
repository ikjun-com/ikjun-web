package com.ikjunweb.controller;

import com.ikjunweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/ikjun/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @ResponseBody
    @PostMapping("/ikjun/loginForm/usernameCheck")
    public boolean usernameCheck(String username) {
        boolean result = userService.isUsernameOverlap(username);
        return result;
    }

    @ResponseBody
    @PostMapping("/ikjun/loginForm/emailCheck")
    public boolean emailCheck(String email) {
        boolean result = userService.isEmailOverLap(email);
        return result;
    }

    @ResponseBody
    @PostMapping("/ikjun/loginForm/nickNameCheck")
    public boolean nickNameCheck(String nickname) {
        boolean result = userService.isNicknameOverLap(nickname);
        return result;
    }
}