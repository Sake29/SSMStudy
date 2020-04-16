package com.sake.jdbctemplate;

import com.sake.dao.IAccountDao;
import com.sake.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdbcTemplate最基本的用法
 */
public class JdbcTemplateDemo4 {


    public static void main(String[] args) {
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountDao accountDao = ac.getBean("accountDao",IAccountDao.class);
        Account account = accountDao.findAccountById(1);

        account.setMoney(10000f);
        accountDao.updateAccount(account);


        System.out.println(account);

    }

}
