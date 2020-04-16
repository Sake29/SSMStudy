package com.sake.dao.impl;

import com.sake.dao.IAccountDao;
import org.springframework.stereotype.Repository;

/**
 * 账户的持久层
 */

@Repository("accountDao2")
public class AccountDaoImpl2 implements IAccountDao {


    public void saveAccount() {
        System.out.println("数据存储成功！2222222");
    }
}
