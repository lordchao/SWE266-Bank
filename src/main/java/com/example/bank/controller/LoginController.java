package com.example.bank.controller;

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
    private JdbcTemplate jdbcTemplate;


    @RequestMapping("/login")
    public String login(String username, String password) {
        System.out.println(username + password);
        Map<String, Object> resultMap = jdbcTemplate.queryForMap("select password from user where username='" + username+"'");
        System.out.println(resultMap.get("password"));
        return "login";
    }
}
