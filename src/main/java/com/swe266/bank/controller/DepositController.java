package com.swe266.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

public class DepositController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/deposit")
    public boolean deposit(String username, double money) {
        if(money<0){
            System.out.println("Please enter the valid money");
            return false;
        }
        Double deposit = Double.valueOf(String.valueOf(jdbcTemplate.queryForMap("select balance from deposit where username=?", username)));
        jdbcTemplate.update("update deposit set balance=? where username=?", money+deposit, username);
        return true;
    }
}
