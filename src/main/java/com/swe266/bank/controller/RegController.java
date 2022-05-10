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
    public String register(String username, String password) {
        boolean regStatus = RegServiceI.register(username, password);
        return "index";
    }
    @RequestMapping("/jump")
    public String jump() {
        return "register";
    }

}
