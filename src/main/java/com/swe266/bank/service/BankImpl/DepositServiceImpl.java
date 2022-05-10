package com.swe266.bank.service.BankImpl;


import com.swe266.bank.service.BankI.DepositServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Map;

@Service("DepositService")
public class DepositServiceImpl implements DepositServiceI {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    public boolean deposit(String username, String money) {

        String sql = "select deposit from user where username ='"+username+"'";
        logger.info(sql);
        Map<String, Object> resultMap = jdbcTemplate.queryForMap(sql);
        Integer balance = (Integer) resultMap.get("deposit");
        Integer new_deposit = Integer.valueOf(money);
        if(new_deposit<0)
            return false;

        sql = "update user set deposit="+money+" where username='"+username+"'";
        logger.info(sql);
        jdbcTemplate.update(sql);
        return true;
    }
}
