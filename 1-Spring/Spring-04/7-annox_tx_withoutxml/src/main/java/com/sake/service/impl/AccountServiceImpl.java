package com.sake.service.impl;

import com.sake.dao.IAccountDao;
import com.sake.domain.Account;
import com.sake.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)//只读型事务控制
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    public Account findAccountById(Integer id) {
        return accountDao.findAccountById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
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
