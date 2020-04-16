package com.sake.dao;

import com.sake.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有操作
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 按id查询
     */
    @Select("select * from user where id=#{id}")
    User findByID(int id);
}
