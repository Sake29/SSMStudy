package com.sake.exception;

/**
 * 自定义异常类
 */
public class SysException extends Exception {

    //存储提示信息
    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SysException(String message) {
        System.out.println("SysException初始化了，参数:"+message);
        this.message = message;
    }
}
