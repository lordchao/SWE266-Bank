package com.swe266.bank.service.BankImpl;


import com.swe266.bank.service.BankI.DepositServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Map;
import java.util.regex.Pattern;

@Service("DepositService")
public class DepositServiceImpl implements DepositServiceI {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    private static final Pattern PATTERN = Pattern.compile("^(([1-9]{1}\\d*)|(0{1}))(\\.\\d{1,2})?$");

    public boolean deposit(String username, String money) {

        String sql = "select deposit from user where username ='"+username+"'";
        logger.info(sql);
        Map<String, Object> resultMap = jdbcTemplate.queryForMap(sql);
        Double balance = (Double) resultMap.get("deposit");
        Double new_deposit = Double.valueOf(money);
        if(new_deposit<0 || !PATTERN.matcher(money).matches()){
            System.out.println("Please enter the valid money");
            return false;
        }
        Double deposit = balance+new_deposit;

        sql = "update user set deposit="+deposit+" where username='"+username+"'";
        logger.info(sql);
        jdbcTemplate.update(sql);
        return true;
    }

    public String checkBalance(String username) {
        Map<String, Object> resMap = jdbcTemplate.queryForMap("select deposit from user where username='"+username+"'");
        return resMap.get("deposit").toString();
    }
}
