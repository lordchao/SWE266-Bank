package com.swe266.bank.service.BankImpl;

import com.swe266.bank.service.BankI.LoginServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("LoginService")
public class LoginServiceImpl implements LoginServiceI {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean login(String username, String password) {
        Map<String, Object> resultMap = jdbcTemplate.queryForMap("select * from user");
        System.out.println(resultMap.get("password"));
        return false;
    }
}
