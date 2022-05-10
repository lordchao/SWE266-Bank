package com.swe266.bank.controller;

import com.swe266.bank.service.BankI.RegServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class RegController {
    @Autowired
    private RegServiceI RegServiceI;

    @RequestMapping("/register")
    public String register(String username, String password, Model model) {
        boolean regStatus = RegServiceI.register(username, password);
        if (regStatus) {
            model.addAttribute("status", "true");
        } else {
            model.addAttribute("status", "false");
        }
        return regStatus ? "index" : "register";
    }
}
