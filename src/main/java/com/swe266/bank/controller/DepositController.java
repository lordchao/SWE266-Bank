package com.swe266.bank.controller;

import com.swe266.bank.service.BankI.DepositServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class DepositController {
    @Autowired
    private DepositServiceI depositServiceI;



    @RequestMapping(value = "/deposit")
    public String deposit(String deposit_amount, Model model) {
        boolean depositStatus = depositServiceI.deposit(deposit_amount);
        if (depositStatus) {
            model.addAttribute("message", "deposit "+deposit_amount+" successfully");
            return "success";
        }
        else {
            model.addAttribute("msg", "please input valid amount");
            return "error";
        }
    }

    @RequestMapping(value="check_balance")
    public String checkBalance(Model model) {
        String balance = depositServiceI.checkBalance();
        model.addAttribute("message", "current balance is: "+balance);
        return "success";
    }
}
