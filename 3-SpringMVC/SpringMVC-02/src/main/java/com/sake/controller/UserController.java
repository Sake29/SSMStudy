package com.sake.controller;

import com.sake.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger=org.apache.log4j.Logger.getLogger(UserController.class);

    @RequestMapping("/testString")
    public String testString(Model model){
        logger.debug("testString执行了...");

        //模拟从数据库中查询出User对象
        User user=new User();
        user.setUsername("王");
        user.setPassword("123");
        user.setAge(12);
        //model对象
        model.addAttribute("user",user);
        return "success";
    }

    /**
     * 返回值为void
     * 如果传入参数为空的话会报错
     * /WEB-INF/pages/user/testVoid.jsp 404
     * 因为void类型会默认给你跳转到testVoid.jsp,如果没有jsp就会报错
     *
     * 解决办法：1.请求转发 2.重定向 3.直接响应
     */
    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("testVoid执行了...");
        //1.请求转发
        //request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request,response);

        //2.重定向
        //request.getContextPath()可以获取当前项目的路径
        //System.out.println(request.getContextPath());
        //response.sendRedirect(request.getContextPath()+"/index.jsp");
        //System.out.println("已重定向到"+request.getContextPath()+"/index.jsp页面");

        //3.直接响应
        //设置中文乱码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print("hello,你好");
        return;
    }

    /**
     * 返回modelandview
     * @return modelAndView
     */
    @RequestMapping("/testModelAndView")
    public org.springframework.web.servlet.ModelAndView testModelAndView(){
        logger.debug("testModelAndView执行了...");
        ModelAndView modelAndView =new ModelAndView();

        //模拟从数据库中查询出User对象
        User user=new User();
        user.setUsername("王");
        user.setPassword("123");
        user.setAge(12);

        //modelAndView两大功能：
        //1.把user对象存储到modelAndView对象中
        modelAndView.addObject("user",user);

        //2.跳转到哪个页面
        modelAndView.setViewName("success");

        return modelAndView;
    }

    /**
     * 使用关键字的方式进行转发或者重定向
     * @return
     */
    @RequestMapping("testForwardOrRedirect")
    public String testForwardOrRedirect(){
        logger.debug("testForwardOrRedirect执行了...");

        //请求转发
        //logger.debug("请求转发了...");
        //return "forward:/WEB-INF/pages/success.jsp";

        //重定向
        logger.debug("重定向了...");
        return "redirect:/index.jsp";
    }


    /**
     * 模拟异步请求响应
     * @param user
     * @return
     */
    @RequestMapping("testAjax")
    public @ResponseBody User testAjax(@RequestBody User user){
        logger.debug("testAjax执行了..");
        //客户端发送ajax的请求，穿的是json字符串，后端把json字符串封装到user对象中
        System.out.println(user);
        //做响应，模拟查询数据库
        user.setUsername("haha");
        user.setAge(300);
        //做响应
        return user;
    }
}
