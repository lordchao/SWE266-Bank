package com.swe266.bank.service.BankImpl;

import com.swe266.bank.service.BankI.WithdrawServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("WithdrawService")
public class WithdrawServiceImpl implements WithdrawServiceI {

    @Autowired
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Override
    public boolean Withdraw(String username, double amount) {
        String sql = "select deposit from user where username='"+username+"'";
        logger.info(sql);

        double currentDeposit = (double)jdbcTemplate.queryForMap(sql).get("deposit");
        if (amount > currentDeposit) return false;
        else {
            currentDeposit = currentDeposit - amount;
            sql = "update user set deposit=" + currentDeposit;
            jdbcTemplate.update(sql);
            logger.info(sql);
            return true;
        }
    }
}
