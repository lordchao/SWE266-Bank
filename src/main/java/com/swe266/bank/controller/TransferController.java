package com.swe266.bank.controller;

import com.swe266.bank.service.BankI.TransferServiceI;
import com.swe266.bank.service.BankImpl.LoginServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TransferController {

    @Autowired
    private TransferServiceI transferServiceI;

    @RequestMapping("/transfer")
    public String transfer(String username, String transfer_amount, String thisUsername, Model model) {
        boolean transferStatus = transferServiceI.transfer(username, transfer_amount, thisUsername);
        if (transferStatus) {
            model.addAttribute("message", "transfer "
                    +transfer_amount+" dollars to user "+ username + " successfully");
            return "success";
        }
        else {
            model.addAttribute("msg", "please check input amount or username");
            return "error";
        }
    }
}
