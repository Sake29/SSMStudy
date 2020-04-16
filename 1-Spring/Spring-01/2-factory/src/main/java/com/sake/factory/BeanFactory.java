package com.sake.factory;


import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 创建bean对象的工厂
 *
 * Bean：可重用组件
 * JavaBean：用Java语言编写的可重用组件
 *       eg：实体类
 *
 * 它就是创建我们的service和dao对象的。
 *
 * 第一步：需要一个配置文件来配置我们的service和dao
 *        配置的内容：唯一标志=全限定类名（key:value）
 * 第二步：通过读取配置文件中配置的内容，反射创建对象
 *
 *
 */
public class BeanFactory {

    //定义一个Properties对象
    private static Properties props;
    //定义一个Map，用于存放我们要创建的对象。
    private static Map<String,Object> beans;


    //使用静态代码块为Properties对象赋值
    static {
        try {
            //实例化对象
            props = new Properties();
            //获取Properties文件的流对象
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(in);
            //实例化容器
            beans = new HashMap<String, Object>();
            //取出配置文件中所有的key
            Enumeration keys = props.keys();
            //遍历枚举
            while (keys.hasMoreElements()){
                //取出每个key
                String key = keys.nextElement().toString();
                //根据key获取value
                String beanPath = props.getProperty(key);
                //反射创建对像
                Object value = Class.forName(beanPath).newInstance();
                //把key和value存入容器中
                beans.put(key,value);
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化properties失败");
        }
    }

    /**
     * 根据bean的名称，获取bean对象
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName){
        return beans.get(beanName);
    }

   /* *//**
     * 根据bean的名称，获取bean对象
     * @param beanName
     * @return
     *//*
    public static Object getBean(String beanName){
        Object bean = null;
       try{
           String beanPath = props.getProperty(beanName);
           //System.out.println("beanName:"+beanName+",beanPath:"+beanPath);
           bean = Class.forName(beanPath).newInstance();//newInstance()每次都会调用默认构造函数
       }catch (Exception e){
           e.printStackTrace();
       }

        return bean;
    }*/
}
