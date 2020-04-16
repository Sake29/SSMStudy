package com.sake.controller;

import com.sake.exception.SysException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger =org.apache.log4j.Logger.getLogger(UserController.class);

    @RequestMapping("/testException")
    public String testException() throws SysException{
        logger.debug("testException执行了....");

        try{
            //模拟异常
            int a =10/0;
        }catch (Exception e)
        {
            //e.printStackTrace();
            e.getMessage();
            //抛出自定义异常信息
            throw new SysException("查询用户出错了...");
        }

        return "success";
    }
}
