package com.sake.mybatis.proxy;

import com.sake.mybatis.cfg.Mapper;
import com.sake.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

public class MapperProxy implements InvocationHandler {

    private Map<String, Mapper> mappers;
    private Connection connection;

    public MapperProxy(Map<String,Mapper> mappers){
        System.out.println("mappers:"+mappers);
        this.mappers=mappers;
    }

    /**
     * 用于对方法进行增强，我们的增强其实就是调用selectList方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //1.获取方法名
        String methodName = method.getName();
        //2.获取方法所在类的名称
        String className = method.getDeclaringClass().getName();
        //3.组合key
        String key = className+"."+methodName;
        System.out.println(key);
        //4.获取mappers中的Mapper对象
        Mapper mapper = mappers.get(key);
        System.out.println("mapper:"+mapper);
        //5.判断是否有mapper
        if(mapper==null){
            throw new IllegalArgumentException("传入的参数有误");
        }
        //6.调用工具类，执行查询所有

        return new Executor().selectList(mapper,connection);
    }
}
