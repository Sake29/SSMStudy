package com.sake.test;


import com.sake.dao.IUserDao;
import com.sake.domain.User;
import com.sake.mybatis.io.Resources;
import com.sake.mybatis.sqlsession.SqlSession;
import com.sake.mybatis.sqlsession.SqlSessionFactory;
import com.sake.mybatis.sqlsession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * mybatis的入门案例
 */
public class MybatisTest {

    private final static Logger logger = Logger.getLogger(MybatisTest.class);

    public static void main(String[] args) throws Exception {

        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("mybatisConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        //6.释放资源
        session.close();
        in.close();
    }

    @Test
    public void test1() throws IOException {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("mybatisConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        //5.使用代理对象执行方法
        for (int i = 1; i < 6; i++) {
            System.out.println(userDao.findByID(i));
            logger.info("------------------------------------------查询结果-------------------------------------------------------------");
            logger.info(userDao.findByID(i));
            logger.info("---------------------------------------------------------------------------------------------------------------");
        }
    }
}
