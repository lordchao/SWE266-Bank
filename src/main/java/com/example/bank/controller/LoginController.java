package com.example.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String jump(Model model) {
        model.addAttribute("msg","你好");
        return "login";
    }
}
