package com.swe266.bank.service.BankImpl;

import com.swe266.bank.service.BankI.WithdrawServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("WithdrawService")
public class WithdrawServiceImpl implements WithdrawServiceI {

    private JdbcTemplate jdbcTemplate;
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Override
    public boolean Withdraw(String username, int amount) {
        String sql = "select deposit from user where username='"+username+"'";
        logger.info(sql);
        Map<String, Object> resultMap = jdbcTemplate.queryForMap(sql);
        String currentDeposit = resultMap.get("deposit").toString();
        if (amount > Integer.parseInt(currentDeposit)) return false;
        int currentDeposit1 = Integer.parseInt(currentDeposit) - amount;
        sql = "update user set deposit="+currentDeposit;
        jdbcTemplate.update(sql);
        logger.info(sql);
        return true;
    }
}
