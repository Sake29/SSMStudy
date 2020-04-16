package com.sake.mybatis.io;


import java.io.InputStream;

/**
 * 使用类加载器读取配置文件的类
 */
public class Resources {

    public static InputStream getResourceAsStream(String filepath){
        return Resources.class.getClassLoader().getResourceAsStream(filepath);
    }
}
