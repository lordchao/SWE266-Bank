package com.swe266.bank.controller;

import com.swe266.bank.service.BankI.DepositServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class DepositController {
    @Autowired
    private DepositServiceI depositServiceI;

    @RequestMapping("/deposit")
    public String deposit(Integer id, String money) {
        depositServiceI.deposit(id, money);
        return "deposit";
    }
}
