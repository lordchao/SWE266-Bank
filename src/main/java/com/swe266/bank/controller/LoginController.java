package com.swe266.bank.controller;

import com.swe266.bank.service.BankI.LoginServiceI;
import com.swe266.bank.service.BankImpl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

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
