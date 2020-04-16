package com.sake.test;

import com.sake.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

    @Test
    public void test1(){
        //加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        //调用方法
        as.findAll();
    }
}
