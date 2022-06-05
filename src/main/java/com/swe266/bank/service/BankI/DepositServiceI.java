package com.swe266.bank.service.BankI;

import org.springframework.ui.Model;

public interface DepositServiceI {
    boolean deposit(String money);
    String checkBalance();
}
