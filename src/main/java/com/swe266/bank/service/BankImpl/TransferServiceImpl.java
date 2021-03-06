package com.swe266.bank.service.BankImpl;


import com.swe266.bank.service.BankI.TransferServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
import java.util.regex.Pattern;


@Service("TransferService")
public class TransferServiceImpl implements TransferServiceI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    HttpSession session;
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    private static final Pattern p = Pattern.compile("^(([1-9]{1}\\d*)|(0{1}))(\\.\\d{1,2})?$");

    @Override
    public boolean transfer(String username, String transfer_amount) {
        String thisUsername = (String) session.getAttribute("username");
        if (username.equals(thisUsername)) {
            return false;
        }
        logger.info("Get username from session--------------" + (String) session.getAttribute("username"));

        logger.info(username,thisUsername,transfer_amount);
        logger.info("select deposit from user where username='"+username+"'");

        List<Map<String, Object>> userResultMap = jdbcTemplate.queryForList("select username from user ");
        Set<String> userNames = new HashSet<>();
        for (Map<String, Object> row: userResultMap)
            userNames.add(row.get("username").toString());
        logger.info(userNames.toString());
        if (!userNames.contains(username)) return false;

        if (! p.matcher(transfer_amount).matches()){
            logger.info("Invalid amount!");
            return false;
        }
        double money = Integer.parseInt(transfer_amount);
        Map<String, Object> resultMap = jdbcTemplate.queryForMap("select deposit from user where username='"+username+"'");

        double toBalance = (double) resultMap.get("deposit") + money;
        Map<String, Object> resultMap1 = jdbcTemplate.queryForMap("select deposit from user where username='"+thisUsername+"'");
        double myBalance = (double) resultMap1.get("deposit") - money;

        if (myBalance<0)
            return false;

        String transactionSql = "insert into transaction(username, transaction_type, amount, time)\n" +
                "\tvalues('" + username + "', 'transfer',"+ transfer_amount +", from_unixtime(unix_timestamp()))";
        jdbcTemplate.update("update user set deposit=? where username=?", toBalance, username);
        jdbcTemplate.update("update user set deposit=? where username=?", myBalance, thisUsername);
        jdbcTemplate.execute(transactionSql);
        logger.info(username+" transfer " + transfer_amount+" successfully");
        return true;
    }
}