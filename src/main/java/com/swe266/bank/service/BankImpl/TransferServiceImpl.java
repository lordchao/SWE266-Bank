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

public class TransferServiceImpl implements TransferServiceI {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Override
    public boolean transfer(String username, String amount, String thisUsername) {
        logger.info("into transfer function");
        String sql = "SELECT deposit FROM user WHERE id = '"+username+"'";
        int myDeposit = jdbcTemplate.queryForObject(sql, Integer.class, thisUsername) - Integer.parseInt(amount);
        int currentDeposit = jdbcTemplate.queryForObject(sql, Integer.class, username);
        int newDeposit = Integer.parseInt(amount)+currentDeposit;
        String sql2 = "update user set deposit =" + myDeposit + "where id ='"+thisUsername+"'";
        String sql3 = "update user set deposit =" + newDeposit + "where id ='"+username+"'";
        jdbcTemplate.execute(sql2);
        jdbcTemplate.execute(sql3);
        return true;
    }
}