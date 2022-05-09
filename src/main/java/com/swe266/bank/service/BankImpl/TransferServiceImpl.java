package com.swe266.bank.service.BankImpl;


import com.swe266.bank.service.BankI.TransferServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class TransferServiceImpl implements TransferServiceI {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean transfer(String username, String amount, String thisUsername) {
        String sql = "SELECT deposit FROM deposit WHERE id = "+username ;

        int myDeposit = jdbcTemplate.queryForObject(sql, Integer.class, thisUsername) - Integer.parseInt(amount);
        int currentDeposit = jdbcTemplate.queryForObject(sql, Integer.class, username);
        int newDeposit = Integer.parseInt(amount)+currentDeposit;
        String sql2 = "update deposit set deposit =" + myDeposit + "where id =" + thisUsername;
        String sql3 = "update deposit set deposit =" + newDeposit + "where id =" + username;
        jdbcTemplate.execute(sql2);
        jdbcTemplate.execute(sql3);
        return true;
    }
}