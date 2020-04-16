package com.sake.service;

import com.sake.domain.Account;

/**
 * 账户的业务层接口
 */
public interface IAccountService {

    Account findAccountById(Integer id);

    void transfer(String source,String target,Float money);

}
