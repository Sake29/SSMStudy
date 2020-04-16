package com.sake.service.impl;

import com.sake.dao.IAccountDao;
import com.sake.dao.impl.AccountDaoImpl;
import com.sake.service.IAccountService;

/**
 * 账户的业务层实现类
 *
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao = new AccountDaoImpl();

    public AccountServiceImpl(){
        System.out.println("AccountServiceImpl对象生成了...");
    }

    public void saveAccount() {
        accountDao.saveAccount();
    }
}
