package com.swe266.bank.service.BankImpl;


import com.swe266.bank.service.BankI.DepositServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("DepositService")
public class DepositServiceImpl implements DepositServiceI {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean deposit(Integer id, String money) {
//        if(money<0){
//            System.out.println("Please enter the valid money");
//            return false;
//        }
        Map<String, Object> resultMap = jdbcTemplate.queryForMap("select * from user");
        System.out.println(resultMap.get("deposit"));
        id = (Integer) resultMap.get("id");

//        Integer deposit = Integer.valueOf(String.valueOf(jdbcTemplate.queryForMap("select deposit from user where id=?;", id)));
//        Integer money_d = Integer.valueOf((String) resultMap.get("deposit"));
//        jdbcTemplate.update("update user set balance=? where id=?", money_d, id);
        return true;
    }
}
