package com.ikjunweb.controller;

import com.ikjunweb.entity.user.User;
import com.ikjunweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/ikjun/user/myprofile")
    public String userDetail() {
        return "/user/myprofile";
    }

    @ResponseBody
    @PostMapping("/ikjun/loginForm/usernameCheck")
    public boolean usernameCheck(@RequestParam("username") String username) {
        boolean result = userService.isUsernameOverlap(username);
        return result;
    }

    @ResponseBody
    @PostMapping("/ikjun/loginForm/emailCheck")
    public boolean emailCheck(@RequestParam("email") String email) {
        boolean result = userService.isEmailOverLap(email);
        return result;
    }

    @ResponseBody
    @PostMapping("/ikjun/loginForm/nicknameCheck")
    public boolean nickNameCheck(@RequestParam("nickname") String nickname) {
        boolean result = userService.isNicknameOverLap(nickname);
        return result;
    }
}