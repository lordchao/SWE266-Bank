package com.swe266.bank.service.BankImpl;

import com.swe266.bank.service.BankI.LoginServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Service("LoginService")
public class LoginServiceImpl implements LoginServiceI {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    public boolean login(String username, String password) {
        Map<String, Object> resultMap = jdbcTemplate.queryForMap("select password from user where username= '"+username+"'");
        logger.info("select password from user where username='"+username+"'" + " executed successfully");
        return resultMap.get("password").equals(password);
    }
}
