package com.sake.test;


import com.sake.dao.IUserDao;
import com.sake.domain.QueryVo;
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
import java.util.ArrayList;
import java.util.List;

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
     * 测试按条件查询
     */
    @Test
    public void testFindByCondition(){
        User u = new User();
        //u.setUsername("sake");
        u.setSex("女");

        List<User> users = userDao.findUserByCondition(u);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试foreach子标签
     */
    @Test
    public void testFindInIds(){
        QueryVo vo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(11);
        list.add(12);
        list.add(14);
        vo.setIds(list);

        List<User> users = userDao.findUserInIds(vo);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
