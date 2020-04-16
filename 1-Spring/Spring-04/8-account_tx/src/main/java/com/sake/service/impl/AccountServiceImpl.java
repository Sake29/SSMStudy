package com.sake.service.impl;

import com.sake.dao.IAccountDao;
import com.sake.domain.Account;
import com.sake.service.IAccountService;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;
    private TransactionTemplate transactionTemplate;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Account findAccountById(final Integer id) {
        return transactionTemplate.execute(new TransactionCallback<Account>() {
            public Account doInTransaction(TransactionStatus transactionStatus) {
                return accountDao.findAccountById(id);
            }
        });
    }

    public void transfer(final String sourceName, final String targetName, final Float money) {
        transactionTemplate.execute(new TransactionCallback<Object>() {
            public Object doInTransaction(TransactionStatus transactionStatus) {
                Account source = accountDao.findAccountByName(sourceName);
                Account target = accountDao.findAccountByName(targetName);
                source.setMoney(source.getMoney()-money);
                target.setMoney(target.getMoney()+money);

                accountDao.updateAccount(source);
                //int i = 1/0;
                accountDao.updateAccount(target);
                return null;
            }
        });
    }
}
