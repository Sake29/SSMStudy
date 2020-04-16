package com.sake.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//控制器
@Controller
@RequestMapping("/system")
public class SystemController {

    @RequestMapping("/login")
    public void tologin(){
        System.out.println("跳转到login.jsp页面了...");
    }

    @RequestMapping("/param")
    public String toParam(){
        System.out.println("跳转到param.jsp页面了...");
        return "system/param";
    }

    @RequestMapping("/anno")
    public void toAnno(){
        System.out.println("跳转到anno.jsp页面了...");
    }

    @RequestMapping(value = "/submit",method = RequestMethod.GET)
    public ModelAndView check_login(ModelAndView model){
        System.out.println("正在验证登录");
        model.setViewName("/system/success");
        model.addObject("user","Sake");
        return model;
    }
}
