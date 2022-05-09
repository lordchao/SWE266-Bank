package com.swe266.bank.controller;

import com.swe266.bank.service.BankI.LoginServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    private LoginServiceI loginServiceI;

    @RequestMapping("/login")
    public String login(String username, String password) {
        loginServiceI.login(username, password);
        return "login";
    }
}