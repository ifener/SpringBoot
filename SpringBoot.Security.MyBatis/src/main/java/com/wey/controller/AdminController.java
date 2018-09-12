package com.wey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admins")
public class AdminController {
    
    @GetMapping("/index")
    public String index() {
        return "admins/index";
    }
}
