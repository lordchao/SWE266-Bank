package com.swe266.bank.service.BankImpl;

import com.swe266.bank.service.BankI.RegServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


@Service("RegService")
public class RegServiceImpl implements RegServiceI {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final Logger logger = Logger.getLogger("SWE266-Bank: ");


    public boolean valid(String str) {
        for (int i = 0; i < str.length(); i++) {
            String c = String.valueOf(str.charAt(i));
            if (!c.matches("[_\\-\\.0-9a-z]")) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean register(String username, String password, String balance) {
        if (username == null || username.length() == 0 || password == null || password.length() == 0) {
            logger.info("No input");
            return false;
        }

        if (!valid(username) || username.length() < 1 || username.length() > 128) {
            logger.info("Invalid input of username");
            return false;
        }
        if (!valid(password) || password.length() < 1 || password.length() > 128) {
            logger.info("Invalid input of password");
            return false;
        }
        if (balance.length() == 0) {
            balance = "0";
        } else {
            if (!balance.matches("(0|[1-9][0-9]*)")) {
                logger.info("Invalid input of balance");
                return false;
            }
        }




//        String cmd = "select * from user";
//        logger.info(cmd);
//        List<Map<String, Object>> results = jdbcTemplate.queryForList(cmd);
//        //logger.info(username + " " + password);
//
//        for (Map<String, Object> oneMap : results) {
//            //logger.info(oneMap.get("username") + " " + oneMap.get("password"));
//            if (oneMap.get("username").equals(username)) {
//                logger.info("This username has been registered before, please try another one!");
//
//                return false;
//            }
//
//        }
        List<Map<String, Object>> resultMap = jdbcTemplate.queryForList("select password from user where username= '"+username+"'");
        if (resultMap.size() != 0) {
            logger.info("This username has been registered before, please try another one!");
            return false;
        }


        //insert new user info
        String insertCMD = "insert into user (username, password, deposit) values('" + username + "','" + password + "','" + balance + "')";
        jdbcTemplate.execute(insertCMD);
        logger.info("Register successfully!");

        return true;
    }

}
