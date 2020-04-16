package com.sake.service;

import com.sake.domain.Account;

import java.util.List;

public interface IAccountService {

    /**
     * 查询所有
     * @return
     */
    public List<Account> findAll();

    /**
     * 保存账户信息
     * @param account
     */
    public void saveAccount(Account account);


}
