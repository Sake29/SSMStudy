package com.sake.test;


import com.sake.dao.IAccountDao;
import com.sake.dao.IUserDao;
import com.sake.domain.Account;
import com.sake.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    private static final Logger logger = Logger.getLogger(MybatisTest.class);

    private InputStream inputStream;
    private SqlSession sqlSession;
    private IUserDao userDao;
    private IAccountDao accountDao;

    /**
     * 读取配置文件并初始化
     * @throws IOException
     */
    @Before//用于在测试方法之前执行
    public void init() throws IOException {
        //1.读取字节流，生成字节输入流
        inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
        //2.获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
        accountDao = sqlSession.getMapper(IAccountDao.class);
    }

    /**
     * 释放资源
     * @throws IOException
     */
    @After//用于在测试方法之后执行
    public void destory() throws IOException {
        sqlSession.close();
        inputStream.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFind() throws IOException {
        List<Account> accounts = accountDao.findAll();
       /* for (Account account : accounts) {
            System.out.println("--------------每个account的信息-----------");
            System.out.println(account);
            System.out.println(account.getUser());
        }*/
    }

    /**
     * 根据用户id查询账户列表
     */
    @Test
    public void testFindAccountById(){
        List<Account> accounts = accountDao.findAll();
    }
}
