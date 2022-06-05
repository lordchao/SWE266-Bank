package com.swe266.bank.service.BankImpl;

import com.swe266.bank.service.BankI.WithdrawServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.regex.Pattern;

@Service("WithdrawService")
public class WithdrawServiceImpl implements WithdrawServiceI {

    @Autowired
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();
    private final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    private static final Pattern p = Pattern.compile("^(([1-9]{1}\\d*)|(0{1}))(\\.\\d{1,2})?$");

    @Autowired
    HttpSession session;

    @Override
    public boolean Withdraw(String amount) {
        String username = (String) session.getAttribute("username");
        String sql = "select deposit from user where username='"+username+"'";
        logger.info(sql);
        double transferAmount = Double.parseDouble(amount);
        double currentDeposit = (double) jdbcTemplate.queryForMap(sql).get("deposit");
        if (transferAmount > currentDeposit || !p.matcher(amount).matches()) {
            logger.info("Invalid withdraw!");
            return false;
        }
        else {
            currentDeposit = currentDeposit - transferAmount;
            sql = "update user set deposit=" + currentDeposit;
            String transactionSql = "insert into transaction(username, transaction_type, amount, time)\n" +
                    "\tvalues('" + username + "', 'withdraw',"+ amount +", from_unixtime(unix_timestamp()))";
            jdbcTemplate.update(sql);
            logger.info(sql);
            jdbcTemplate.execute(transactionSql);
            logger.info("user "+username+" withdraw "+amount+" successfully");
            return true;
        }
    }
}
