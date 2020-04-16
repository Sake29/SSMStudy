package com.sake.factory;

import com.sake.service.IAccountService;
import com.sake.service.impl.AccountServiceImpl;

/**
 * 模拟一个工厂类，该类无默认构造函数
 */
public class InstanceFactory {

    public IAccountService getAccountServiceByFactory(){
        return new AccountServiceImpl();
    }
}
