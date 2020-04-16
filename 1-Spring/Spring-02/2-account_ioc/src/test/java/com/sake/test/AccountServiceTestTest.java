package com.sake.test;


import com.sake.domain.Account;
import com.sake.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AccountServiceTestTest {

    @Test
    public void testFindAll(){
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = (IAccountService) ac.getBean("accountService");
        List<Account> accounts = as.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = (IAccountService) ac.getBean("accountService");
        Account account = as.findAccountById(1);
        System.out.println(account);

    }

    @Test
    public void testSave(){
        Account account = new Account();
        account.setName("王");
        account.setMoney(10000.73F);

        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = (IAccountService) ac.getBean("accountService");
        as.saveAccount(account);

    }

    @Test
    public void testUpdate(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = (IAccountService) ac.getBean("accountService");
        Account account = as.findAccountById(4);
        account.setName("王王.");
        as.updateAccount(account);
    }

    @Test
    public void testDelete(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = (IAccountService) ac.getBean("accountService");
        as.deleteAccount(4);
    }

}