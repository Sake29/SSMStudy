package com.sake.dao.impl;

import com.sake.dao.IUserDao;
import com.sake.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory){
        this.factory=factory;
    }

    public List<User> findAll() {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法，实现查询列表
        List<User> users = session.selectList("com.sake.dao.IUserDao.findAll");//参数就是能获取到配置信息的key
        //3.释放资源
        session.close();
        return users;
    }

    public User findById(Integer userId) {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法，实现查询一个
        User user = session.selectOne("com.sake.dao.IUserDao.findById",userId);
        //3.释放资源
        session.close();
        return user;
    }

    public List<User> findByName(String username) {
        return null;
    }

    public int findTotal() {
        return 0;
    }

    public void saveUser(User user) {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用方法实现保存
        session.insert("com.sake.dao.IUserDao.saveUser",user);
        //3.提交事务和关闭资源
        session.commit();
        session.close();
    }

    public void updateUser(User user) {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用方法实现保存
        session.insert("com.sake.dao.IUserDao.updateUser",user);
        //3.提交事务和关闭资源
        session.commit();
        session.close();
    }

    public void deleteUser(Integer userId) {

    }
}
