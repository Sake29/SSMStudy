package com.sake.dao.impl;

import com.sake.dao.IAccountDao;
import com.sake.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AccountDaoImpl extends JdbcDaoSupport implements IAccountDao {



    public Account findAccountById(Integer id) {
        List<Account> accounts = super.getJdbcTemplate().query("select * from account where id =?", new BeanPropertyRowMapper<Account>(Account.class), id);
        return accounts.isEmpty()?null:accounts.get(0);
    }

    public Account findAccountByName(String name) {
        List<Account> accounts = getJdbcTemplate().query("select * from account where name =?", new BeanPropertyRowMapper<Account>(Account.class), name);
        if (accounts.isEmpty()){
            return null;
        }
        if (accounts.size()>1){
            throw new RuntimeException("结果集不唯一");
        }
        return accounts.get(0);
    }

    public void updateAccount(Account account) {
        getJdbcTemplate().update("update account set name=?,money=? where id = ?",account.getName(),account.getMoney(),account.getId());
    }
}
