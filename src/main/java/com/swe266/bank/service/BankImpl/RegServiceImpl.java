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

    @Override
    public boolean register(String username, String password) {
        if (username == null || username.length() == 0 || password == null || password.length() == 0 || username.length() > 10 || password.length() > 12) {
            logger.info("No input");
            return false;
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
        String insertCMD = "insert into user (username, password) values(\"" + username + "\",\"" + password + "\")";
        jdbcTemplate.execute(insertCMD);
        logger.info("Register successfully!");

        return true;
    }
}
