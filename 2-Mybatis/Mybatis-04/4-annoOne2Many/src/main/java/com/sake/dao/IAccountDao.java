package com.sake.dao;

import com.sake.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IAccountDao {

    /**
     * 查询所有的账户，并且获取每个账户所属的用户信息
     * @return
     */
    @Select("select * from account")
    @Results(id="accountMap",value = {
            @Result(id=true, column = "id",property = "id"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "money",property = "money"),
            @Result(property = "user",column = "uid",one = @One(select="com.sake.dao.IUserDao.findUserById",fetchType = FetchType.EAGER/*立即加载*/))
    })
    List<Account> findAll();

    @Select("select * from account where uid=#{userId}")
    List<Account> findAccountByUid(Integer userId);
}
