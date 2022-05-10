package com.swe266.bank.controller;

import com.swe266.bank.service.BankI.TransferServiceI;
import com.swe266.bank.service.BankImpl.LoginServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

public class TransferController {
    @Autowired
    private TransferServiceI TransferServiceI;
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    @RequestMapping("/transfer")
    public String c(String username, String password, String thisUsername) {
        logger.info("HELLO");
        boolean transferStatus = TransferServiceI.transfer(username, password, thisUsername);
        if (transferStatus) return "main";
        else return "error";
    }
}
