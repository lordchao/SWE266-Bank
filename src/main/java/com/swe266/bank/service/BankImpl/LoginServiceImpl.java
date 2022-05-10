package com.swe266.bank.service.BankImpl;

import com.swe266.bank.service.BankI.LoginServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("LoginService")
public class LoginServiceImpl implements LoginServiceI {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    public boolean login(String username, String password) {
        //TODO create a mapping for checking user
        logger.info("select username from user where username='"+username+"'");
        List<Map<String, Object>> userResultMap = jdbcTemplate.queryForList("select username from user ");
        Set<String> userNames = new HashSet<>();
        for (Map<String, Object> row: userResultMap)
            userNames.add(row.get("username").toString());

        if (!userNames.contains(username)) return false;

        Map<String, Object> resultMap = jdbcTemplate.queryForMap("select password from user where username= '"+username+"'");

        logger.info("select password from user where username='"+username+"'" + " executed successfully");
        return resultMap.get("password").equals(password);
    }
}
