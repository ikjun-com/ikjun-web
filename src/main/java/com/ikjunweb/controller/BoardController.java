package com.ikjunweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/ikjun")
    public String home() {
        return "home";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
