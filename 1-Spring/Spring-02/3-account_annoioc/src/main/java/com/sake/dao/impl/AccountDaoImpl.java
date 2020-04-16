package com.sake.dao.impl;

import com.sake.dao.IAccountDao;
import com.sake.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {

    @Autowired
    private QueryRunner runner;

    public List<Account> findAll(){
        try {
            String sql = "select * from account";
            return runner.query(sql,new BeanListHandler<Account>(Account.class));
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Account findAccountById(Integer accountId) {
        try {
            String sql = "select * from account where id=?";
            return runner.query(sql,new BeanHandler<Account>(Account.class),accountId);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void saveAccount(Account account) {
        try {
            String sql = "insert into account(name,money)values(?,?) ";
            runner.update(sql,account.getName(),account.getMoney());
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void updateAccount(Account account) {
        try {
            String sql = "update account set name=?,money=? where id=?";
            runner.update(sql,account.getName(),account.getMoney(),account.getId());
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void deleteAccount(Integer accountId) {
        try {
            String sql = "delete from account where id=?";
            runner.update(sql,accountId);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
