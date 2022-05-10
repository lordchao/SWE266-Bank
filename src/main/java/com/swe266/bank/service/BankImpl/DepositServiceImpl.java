package com.swe266.bank.service.BankImpl;


import com.swe266.bank.service.BankI.DepositServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Pattern;

@Service("DepositService")
public class DepositServiceImpl implements DepositServiceI {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final Pattern PATTERN = Pattern.compile("^(([1-9]{1}\\d*)|(0{1}))(\\.\\d{1,2})?$");

    public boolean deposit(Integer id, String money) {

        Map<String, Object> resultMap = jdbcTemplate.queryForMap("select * from user");
        id = (Integer) resultMap.get("id");
        Double balance = (Double) resultMap.get("deposit");
        Double new_deposit = Double.valueOf(money);
        if(new_deposit<0 || !PATTERN.matcher(money).matches()){
            System.out.println("Please enter the valid money");
            return false;
        }

        jdbcTemplate.update("update user set deposit=? where id=?", balance+new_deposit, id);
        return true;
    }
}
