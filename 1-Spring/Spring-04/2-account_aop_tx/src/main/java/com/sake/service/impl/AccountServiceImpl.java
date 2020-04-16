package com.sake.service.impl;

import com.sake.dao.IAccountDao;
import com.sake.dao.impl.AccountDaoImpl;
import com.sake.domain.Account;
import com.sake.service.IAccountService;
import com.sake.utils.TransactionManager;

import java.util.List;

/**
 * 账户的业务层实现类
 *
 * 事务的控制应该在业务层
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }


    public List<Account> findAll() {
        return accountDao.findAll();
    }

    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);


    }

    public void saveAccount(Account account) {
            accountDao.saveAccount(account);
    }

    public void updateAccount(Account account) {
            accountDao.updateAccount(account);
    }

    public void deleteAccount(Integer accountId) {
            accountDao.deleteAccount(accountId);
    }

    public void transfer(String sourceName, String targetName, float money) {
            //2.1.根据名称查询转出账户
            Account source = accountDao.findAccountByName(sourceName);
            //2.2.根据名称查询转入账户
            Account target = accountDao.findAccountByName(targetName);
            //2.3.转出账户减钱
            source.setMoney(source.getMoney()-money);
            //2.4.转入账户加钱
            target.setMoney(target.getMoney()+money);
            //2.5.更新转出账户
            accountDao.updateAccount(source);

            int i = 1/0;

            //2.6.更新转入账户
            accountDao.updateAccount(target);
    }
}
