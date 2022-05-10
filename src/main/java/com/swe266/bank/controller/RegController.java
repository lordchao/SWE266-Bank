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
    public String register(String username, String password, String balance) {
        boolean regStatus = RegServiceI.register(username, password, balance);
        return regStatus ? "regSuccess" : "regFail";
    }
    @RequestMapping("/jumpReg")
    public String jumpReg() {
        return "register";
    }

    @RequestMapping("/jumpMain")
    public String jumpMain() {
        return "main";
    }
}
