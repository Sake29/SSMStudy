package com.sake.dao;

import com.sake.domain.Account;
import com.sake.domain.AccountUser;

import java.util.List;

public interface IAccountDao {
    /**
     * 查询所有账户
     * @return
     */
    List<Account> findAll();

    /**
     * 查询所有账户,并且带有用户的部分信息
     * @return
     */
    List<AccountUser> findAllAccount();
}
