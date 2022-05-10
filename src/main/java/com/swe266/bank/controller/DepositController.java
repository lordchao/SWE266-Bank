package com.swe266.bank.controller;

import com.swe266.bank.service.BankI.DepositServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class DepositController {
    @Autowired

    private DepositServiceI depositServiceI;

    @RequestMapping(value = "/deposit")
    public String deposit(Integer id, String deposit_amount) {
        boolean depositStatus = depositServiceI.deposit(id, deposit_amount);
        if (depositStatus) return "main";
        else return "error";

    }
}
