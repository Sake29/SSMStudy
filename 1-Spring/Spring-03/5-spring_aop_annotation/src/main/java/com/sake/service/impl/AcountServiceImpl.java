package com.sake.service.impl;

import com.sake.service.IAccountService;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AcountServiceImpl implements IAccountService {
    public void saveAccount() {
        System.out.println("保存了..");
        //int i=1/0;
    }

    public void updateAccount(int i) {
        System.out.println("更新了..."+i);
    }

    public int deleteAccount() {
        System.out.println("删除了..");
        return 0;
    }
}
