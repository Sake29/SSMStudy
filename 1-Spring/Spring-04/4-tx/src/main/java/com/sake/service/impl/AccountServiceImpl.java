package com.sake.service.impl;

import com.sake.dao.IAccountDao;
import com.sake.domain.Account;
import com.sake.service.IAccountService;

public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Account findAccountById(Integer id) {
        return accountDao.findAccountById(id);
    }

    public void transfer(String sourceName, String targetName, Float money) {
        Account source = accountDao.findAccountByName(sourceName);
        Account target = accountDao.findAccountByName(targetName);
        source.setMoney(source.getMoney()-money);
        target.setMoney(target.getMoney()+money);

        accountDao.updateAccount(source);
        //int i = 1/0;
        accountDao.updateAccount(target);
    }
}
