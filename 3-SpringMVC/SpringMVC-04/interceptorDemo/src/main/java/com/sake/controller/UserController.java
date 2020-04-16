package com.sake.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @RequestMapping("/testInterceptor")
    public String testException(){
        logger.debug("testInterceptor执行了...");

        return "success";
    }
}
