package com.sake.dao.impl;

import com.sake.dao.IAccountDao;
import org.springframework.stereotype.Repository;

/**
 * 账户的持久层
 */

@Repository("accountDao1")
public class AccountDaoImpl1 implements IAccountDao {


    public void saveAccount() {
        System.out.println("数据存储成功！1111111");
    }
}
