package com.sake.dao;

import com.sake.domain.User;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

    /**
     * 按id查询
     */
    User findByID(int id);
}
