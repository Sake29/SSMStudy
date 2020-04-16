package com.sake.dao;

import com.sake.domain.Account;

/**
 * 账户的持久层接口
 */
public interface IAccountDao {
    Account findAccountById(Integer id);
    Account findAccountByName(String name);
    void updateAccount(Account account);
}
