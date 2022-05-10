package com.swe266.bank.service.BankImpl;


import com.swe266.bank.service.BankI.TransferServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.swe266.bank.service.BankI.LoginServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service("TransferService")
public class TransferServiceImpl implements TransferServiceI {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    private static final Pattern p = Pattern.compile("^(([1-9]{1}\\d*)|(0{1}))(\\.\\d{1,2})?$");
    @Override
    public boolean transfer(String username, String transfer_amount, String thisUsername) {
        logger.info(username,thisUsername,transfer_amount);
        logger.info("select deposit from user where username='"+username+"'");

        if (! p.matcher(transfer_amount).matches()){
            logger.info("Invalid amount!");
            return false;
        }
        double money = Integer.valueOf(transfer_amount);
        Map<String, Object> resultMap = jdbcTemplate.queryForMap("select deposit from user where username='"+username+"'");

        double toBalance = (int) resultMap.get("deposit") + money;
        Map<String, Object> resultMap1 = jdbcTemplate.queryForMap("select deposit from user where username='"+thisUsername+"'");
        double myBalance = (int) resultMap1.get("deposit") - money;
        if (myBalance<0){
            System.out.println("Not enough money to transfer!");
            return false;
        }
        jdbcTemplate.update("update user set deposit=? where username=?", toBalance, username);
        jdbcTemplate.update("update user set deposit=? where username=?", myBalance, thisUsername);
        return true;
    }
}