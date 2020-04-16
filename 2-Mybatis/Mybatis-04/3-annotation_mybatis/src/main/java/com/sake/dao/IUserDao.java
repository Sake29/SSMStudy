package com.sake.dao;

import com.sake.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * mybatis中CRUD有4个注解
 * @SELECT @INSERT @UPDATE @DELETE
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select(value = "select * from user")
    List<User> findAll();

    /**
     * 根据id查询用户
     * @return
     */
    @Select("select * from user where id=#{userId}")
    User findUserById(Integer userId);

    /**
     * 保存用户
     * @param user
     */
    @Insert("insert into user(username,address,sex,birthday)values(#{username},#{address},#{sex},#{birthday}) ")
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    @Update("update user set username=#{username},sex=#{sex},birthday=#{birthday} where id=#{id}")
    void updateUser(User user);

    /**
     * 删除用户
     * @param userId
     */
    @Delete("delete from user where id=#{userId}")
    void deleteUser(Integer userId);

    @Select("select * from user where username like #{username}")
    List<User> findUserByName(String username);

    /**
     *查询总记录条数
     * @return
     */
    @Select("select count(*) from user")
    int findTotal();
}