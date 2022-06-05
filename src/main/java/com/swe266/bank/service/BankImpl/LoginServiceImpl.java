package com.swe266.bank.service.BankImpl;

import com.swe266.bank.service.BankI.LoginServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("LoginService")
public class LoginServiceImpl implements LoginServiceI {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    HttpSession session;
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    public boolean valid(String str) {
        for (int i = 0; i < str.length(); i++) {
            String c = String.valueOf(str.charAt(i));
            if (!c.matches("[_\\-\\.0-9a-z]")) {
                return false;
            }
        }
        return true;
    }
    public boolean login(String username, String password) {
        //TODO create a mapping for checking user
        if (username == null || username.length() == 0 || password == null || password.length() == 0) {
            logger.info("No input");
            return false;
        }
        if (!valid(username) || username.length() < 1 || username.length() > 128) {
            logger.info("Invalid input of username");
            return false;
        }

        logger.info("select username from user where username='"+username+"'");
        List<Map<String, Object>> userResultMap = jdbcTemplate.queryForList("select username from user ");
        Set<String> userNames = new HashSet<>();
        for (Map<String, Object> row: userResultMap)
            userNames.add(row.get("username").toString());

        if (!userNames.contains(username)) return false;

        // add hash function
        Integer hash = 0;
        for (int i=0; i<password.length(); ++i)
            hash = 33*hash + password.charAt(i);

        Map<String, Object> resultMap = jdbcTemplate.queryForMap("select password from user where username= '"+username+"'");
        String hash_s = String.valueOf(hash);
        logger.info("select password from user where username='"+username+"'" + " executed successfully");
        if (resultMap.get("password").equals(hash_s)) {
            session.setAttribute("username", username);
            logger.info("User logged in.");
        }
        return resultMap.get("password").equals(hash_s);
    }
}
