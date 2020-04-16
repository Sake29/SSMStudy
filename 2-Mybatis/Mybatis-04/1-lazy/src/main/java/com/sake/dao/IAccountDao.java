package com.sake.dao;

import com.sake.domain.Account;

import java.util.List;

public interface IAccountDao {
    /**
     * 查询所有账户
     * @return
     */
    List<Account> findAll();

    List<Account> findAccountByUid(Integer uid);
}
