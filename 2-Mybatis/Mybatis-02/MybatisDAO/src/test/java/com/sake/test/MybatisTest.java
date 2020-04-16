package com.sake.test;


import com.sake.dao.IUserDao;
import com.sake.dao.impl.UserDaoImpl;
import com.sake.domain.User;
import com.sake.utils.DateUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.text.ParseException;
import java.util.List;
import org.apache.log4j.Logger;

public class MybatisTest {

    private static final Logger logger = Logger.getLogger(MybatisTest.class);

    private InputStream inputStream;
    private SqlSession sqlSession;
    private IUserDao userDao;

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
        //3.使用工厂对象创建dao对象
        userDao = new UserDaoImpl(factory);
    }

    /**
     * 释放资源
     * @throws IOException
     */
    @After//用于在测试方法之后执行
    public void destory() throws IOException {
        inputStream.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() throws IOException {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindById(){
        User user = userDao.findById(14);
        System.out.println(user);
    }

    /**
     * 测试插入
     */
    @Test
    public void testsaveUser() throws IOException {

        User newUser = new User();
        newUser.setUsername("中中");
        newUser.setSex("女");
        newUser.setAddress("中国");
        Date date = DateUtils.getDate(1999, 7, 1);
        newUser.setBirthday(date);

        userDao.saveUser(newUser);
        testFindAll();
    }

    /**
     * 测试更新
     */
    @Test
    public void testupdateUser() throws IOException {
        System.out.println("更新前User：");
        testFindById();

        User newUser = new User();
        newUser.setUsername("中中中");
        newUser.setId(14);
        newUser.setSex("女");
        newUser.setAddress("河南");
        Date date = DateUtils.getDate(1999, 7, 1);
        newUser.setBirthday(date);

        userDao.updateUser(newUser);
        System.out.println("更新后User：");
        testFindById();
    }

}
