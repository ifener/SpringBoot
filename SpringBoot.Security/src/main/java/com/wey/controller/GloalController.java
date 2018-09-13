package com.wey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GloalController {
    
    @RequestMapping("/denied")
    public String deny() {
        return "/denied";
    }
}
