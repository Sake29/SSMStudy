package com.sake.test;

import com.sake.dao.IAccountDao;
import com.sake.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {

    @Test
    public void test1() throws IOException {
        //加载mybatis的配置文件
        InputStream in = Resources.getResourceAsStream("mybatisConfig.xml");
        //创建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取代理对象
        IAccountDao dao = sqlSession.getMapper(IAccountDao.class);
        List<Account> accounts = dao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
        //释放连接
        sqlSession.close();
        in.close();
    }

    @Test
    public void test2() throws IOException {
        Account account = new Account();
        account.setName("小王吧");
        account.setMoney(23.21);

        //加载mybatis的配置文件
        InputStream in = Resources.getResourceAsStream("mybatisConfig.xml");
        //创建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取代理对象
        IAccountDao dao = sqlSession.getMapper(IAccountDao.class);
        dao.saveAccount(account);
        sqlSession.commit();
        //释放连接
        sqlSession.close();
        in.close();
    }
}
