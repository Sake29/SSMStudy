package com.sake.dao;

import com.sake.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * mybatis中CRUD有4个注解
 * @SELECT @INSERT @UPDATE @DELETE
 */
@CacheNamespace(blocking = true)
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select(value = "select * from user")
    @Results(id = "userMap",value = {
            @Result(id = true,column = "id",property = "userId"),
            @Result(column = "username",property = "userName"),
            @Result(column = "sex",property = "userSex"),
            @Result(column = "address",property = "userAddress"),
            @Result(column = "birthday",property = "userBirthday"),
            @Result(property = "accounts",column = "id",
                    many = @Many(select = "com.sake.dao.IAccountDao.findAccountByUid",
                            fetchType = FetchType.LAZY/*懒加载*/))
    })
    List<User> findAll();

    /**
     * 根据id查询用户
     * @return
     */
    @Select("select * from user where id=#{userId}")
    @ResultMap(value = {"userMap"})//标准写法
    //@ResultMap('userMap')
    User findUserById(Integer userId);

    /**
     * 根据姓名查询用户
     * @param username
     * @return
     */
    @Select("select * from user where username like #{username}")
    @ResultMap("userMap")
    List<User> findUserByName(String username);

    /**
     *查询总记录条数
     * @return
     */
    @Select("select count(*) from user")
    int findTotal();
}