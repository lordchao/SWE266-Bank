package com.swe266.bank.controller;

import com.swe266.bank.service.BankI.WithdrawServiceI;
import com.swe266.bank.service.BankImpl.LoginServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
public class WithdrawController {

    @Autowired
    WithdrawServiceI withdrawServiceI;

    @RequestMapping("/withdraw")
    String withdraw(String username, String amount, Model model) {
        if (withdrawServiceI.Withdraw(amount)) {
            model.addAttribute("message", "withdraw " + amount+ " dollar successfully");
            return "success";
        } else {
            model.addAttribute("msg", "withdraw failed please check deposit amount");
            return "error";
        }
    }
}
