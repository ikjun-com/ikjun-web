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

    @GetMapping("/ikjun/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @PostMapping("/ikjun/loginForm/usernameCheck")
    public int usernameCheck(String username) {
        int result = userService.isUsernameOverlap(username);
        return result;
    }

    @PostMapping("/ikjun/loginForm/emailCheck")
    public int emailCheck(String email) {
        int result = userService.isEmailOverLap(email);
        return result;
    }

    @PostMapping("/ikjun/loginForm/nickNameCheck")
    public int nickNameCheck(String nickname) {
        int result = userService.isNicknameOverLap(nickname);
        return result;
    }
}
