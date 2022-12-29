package com.ikjunweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormController {

    @GetMapping("/ikjun/registerForm")
    public String registerForm() {
        return "registerForm";
    }

    @GetMapping("/ikjun/loginForm")
    public String loginForm() {
        return "loginForm";
    }
}
