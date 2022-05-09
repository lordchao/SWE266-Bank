package com.swe266.bank.controller;

import com.swe266.bank.service.BankI.RegServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class RegController {
    @Autowired
    private RegServiceI RegServiceI;

    @RequestMapping("/register")
    public String login(String username, String password) {
        RegServiceI.register(username, password);
        return "register";
    }
}
