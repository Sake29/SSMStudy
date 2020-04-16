package com.sake.ui;

import com.sake.dao.IAccountDao;
import com.sake.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as1 = (IAccountService) ac.getBean("accountService");
        //IAccountService as2 = (IAccountService) ac.getBean("accountService");
/*        System.out.println(as);

        IAccountDao adao = (IAccountDao) ac.getBean("accountDao");
        System.out.println(adao);*/
        as1.saveAccount();
        ac.close();

        //System.out.println(as1==as2);
    }
}
