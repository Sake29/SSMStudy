package com.sake.mybatis.sqlsession;


import com.sake.mybatis.cfg.Configuration;
import com.sake.mybatis.defaults.DefaultSqlSessionFactory;

import java.io.InputStream;

/**
 * 用于创建一个SqlSessionFactory对象
 */
public class SqlSessionFactoryBuilder {

    /**
     * 根据参数的字节输入流来构建一个SqlSessionFactory工厂
     * @param config
     * @return
     */
    public SqlSessionFactory build(InputStream config){
        Configuration cfg = com.sake.mybatis.utils.XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }
}
