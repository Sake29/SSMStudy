package com.sake.test;


import com.sake.dao.IUserDao;
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
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
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
    public void testFindAll() throws IOException {
        List<User> users = userDao.findAll();
        /*for (User user : users) {
            System.out.println(user);
        }*/
    }

    /**
     * 测试通过id查询
     */
    @Test
    public void testFindById() {
        User user = userDao.findById(1);
        System.out.println(user);
    }

    /**
     * 测试姓名模糊查询
     */
    @Test
    public void testFindByName() {
        List<User> users = userDao.findByName("%小%");
        //List<User> users = userDao.findByName("小");
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试总记录条数查询
     */
    @Test
    public void testFindTotal() {
        int total = userDao.findTotal();
        System.out.println("user表中共有"+total+"条记录");
    }

    /**
     * 测试保存操作
     */
    @Test
    public void testSaveUser() throws IOException {

        User user = new User();
        user.setUsername("小月月");
        user.setAddress("广东省珠海市");
        user.setSex("女");
        Date date = DateUtils.getDate(1997,6,3);
        user.setBirthday(date);

        System.out.println(user);
        userDao.saveUser(user);
        //手动提交事务
        sqlSession.commit();

        List<User> users = userDao.findAll();
        for (User user1 : users) {
            logger.info(user1);
        }
        System.out.println(user);
    }

    /**
     * 测试更新操作
     */
    @Test
    public void testUpdateUser() throws IOException {

        User user = new User();
        user.setId(10);
        user.setUsername("小月月");
        user.setAddress("广东省珠海市");
        user.setSex("女");
        Date date = DateUtils.getDate(1997,6,3);
        user.setBirthday(date);

        userDao.updateUser(user);
        //手动提交事务
        sqlSession.commit();

        List<User> users = userDao.findAll();
        for (User user1 : users) {
            logger.info(user1);
        }
    }

    /**
     * 测试删除操作
     */
    @Test
    public void testDeleteUser() throws IOException {
        userDao.deleteUser(10);
        //手动提交事务
        sqlSession.commit();

        List<User> users = userDao.findAll();
        for (User user1 : users) {
            logger.info(user1);
        }
    }

    @Test
    public void test(){
        /*long time =  DateUtils.date2Millis(2017,6,27,15,9,0);
        System.out.println(time);
        System.out.println(new Date(time));
        System.out.println(new java.sql.Timestamp(time));*/
        Date date = DateUtils.getDate(1997,13,32);
        System.out.println(date);

    }
}
