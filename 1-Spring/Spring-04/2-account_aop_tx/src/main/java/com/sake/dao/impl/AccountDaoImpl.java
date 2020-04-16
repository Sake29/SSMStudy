package com.sake.dao.impl;

import com.sake.dao.IAccountDao;
import com.sake.domain.Account;
import com.sake.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class AccountDaoImpl implements IAccountDao {

    private QueryRunner runner;
    private ConnectionUtils connectionUtils;

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    public List<Account> findAll(){
        try {
            String sql = "select * from account";
            return runner.query(connectionUtils.getThreadConnection(),sql,new BeanListHandler<Account>(Account.class));
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Account findAccountById(Integer accountId) {
        try {
            String sql = "select * from account where id=?";
            return runner.query(connectionUtils.getThreadConnection(),sql,new BeanHandler<Account>(Account.class),accountId);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void saveAccount(Account account) {
        try {
            String sql = "insert into account(name,money)values(?,?) ";
            runner.update(connectionUtils.getThreadConnection(),sql,account.getName(),account.getMoney());
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void updateAccount(Account account) {
        try {
            String sql = "update account set name=?,money=? where id=?";
            runner.update(connectionUtils.getThreadConnection(),sql,account.getName(),account.getMoney(),account.getId());
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void deleteAccount(Integer accountId) {
        try {
            String sql = "delete from account where id=?";
            runner.update(connectionUtils.getThreadConnection(),sql,accountId);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Account findAccountByName(String accountName) {
        try {
            String sql = "select * from account where name=?";
            List<Account> accounts = runner.query(connectionUtils.getThreadConnection(),sql,new BeanListHandler<Account>(Account.class),accountName);
            if (accounts==null||accounts.size()==0){
                return null;
            }
            if (accounts.size()>1){
                throw new RuntimeException("结果集不唯一,数据有问题");
            }
            return accounts.get(0);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
