package com.swe266.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

public class DepositController {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @RequestMapping("/deposit")
    public boolean deposit(Integer id, double money) {
        if(money<0){
            System.out.println("Please enter the valid money");
            return false;
        }
        Double deposit = Double.valueOf(String.valueOf(jdbcTemplate.queryForMap("select balance from user where id=?", id)));
        jdbcTemplate.update("update user set balance=? where id=?", money+deposit, id);
        return true;
    }
}
