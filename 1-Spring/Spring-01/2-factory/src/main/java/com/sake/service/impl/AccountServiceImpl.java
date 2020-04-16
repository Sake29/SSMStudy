package com.sake.service.impl;

import com.sake.dao.IAccountDao;
import com.sake.dao.impl.AccountDaoImpl;
import com.sake.factory.BeanFactory;
import com.sake.service.IAccountService;

/**
 * 账户的业务层实现类
 *
 */
public class AccountServiceImpl implements IAccountService {

    //private IAccountDao accountDao = new AccountDaoImpl();
    private IAccountDao accountDao = (IAccountDao)BeanFactory.getBean("accountDao");



    public void saveAccount() {
        accountDao.saveAccount();
    }
}
