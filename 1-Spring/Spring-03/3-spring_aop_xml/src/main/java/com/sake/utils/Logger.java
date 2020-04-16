package com.sake.utils;

/**
 * 用于记录日志的工具类
 */
public class Logger {

    /**
     * 用于打印日志，计划让其在切入点方法执行之前执行（切入点方法就是业务层方法）
     */
    public void printLog(){
        System.out.println("--------Logger的printLog开始记录日志了----------");
    }

    public void test(){
        System.out.println("---------Logger结束了----------");
    }
}
