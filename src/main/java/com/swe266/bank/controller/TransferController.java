package com.swe266.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

public class TransferController {
    @Autowired
    private com.swe266.bank.service.BankI.TransferServiceI TransferServiceI;

    @RequestMapping("/transfer")
    public String transfer(String username, String password, String thisUsername) {
        TransferServiceI.transfer(username, password, thisUsername);
        return "transfer";
    }
}
