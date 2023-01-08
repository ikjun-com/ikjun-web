package com.ikjunweb.controller;

import com.ikjunweb.entity.user.User;
import com.ikjunweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
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

    @GetMapping("/ikjun/user/{nickname}")
    public String userDetail(@PathVariable String nickname) {
        return "/user/userDetail";
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
    @PostMapping("/ikjun/loginForm/nicknameCheck")
    public boolean nickNameCheck(String nickname) {
        boolean result = userService.isNicknameOverLap(nickname);
        return result;
    }
}