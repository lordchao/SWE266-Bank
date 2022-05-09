package com.swe266.bank.controller;

import com.swe266.bank.service.BankI.LoginServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    private LoginServiceI loginServiceI;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password) {
        boolean loginStatus = loginServiceI.login(username, password);
        if (loginStatus) return "main";
        else return "error";
    }
}
