package com.ikjunweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/ikjun/main")
    public String index() {
        return "home";
    }
}
