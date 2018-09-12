package com.wey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    
    @RequestMapping("/index")
    public String index(Model model) {
        
        return "/home/index";
    }
    
    @RequestMapping("/error")
    public String error() {
        throw new IllegalArgumentException("Illegal Argument Exception");
    }
    
}
