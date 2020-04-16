package com.sake.controller;

import com.sake.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;


@Controller
@RequestMapping("/param")
public class ParamController {

    /**
     * 请求参数绑定吧数据封装到JavaBean中
     * @return
     */
    @RequestMapping("/saveAccount")
    public String saveAccount(Account account) {
        System.out.println("saveAccount执行了...");
        System.out.println(account);
        return "system/success";
    }

    /**
     * 获取servlet
     */
    @RequestMapping("/getServlet")
    public String getServlet(HttpServletRequest request, HttpServletResponse response){

        System.out.println("获取到了request和response");
        HttpSession session = request.getSession();
        Collection<String> headerNames = response.getHeaderNames();
        System.out.println("headerNames:"+headerNames);
        System.out.println("session:"+session);
        System.out.println("request"+request);
        System.out.println("response"+response);
        return "system/success";
    }

}
