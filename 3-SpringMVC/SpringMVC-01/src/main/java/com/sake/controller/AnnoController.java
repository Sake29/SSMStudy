package com.sake.controller;


import com.sake.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;

@Controller
@RequestMapping("/anno")
@SessionAttributes(value = {"msg"})//把msg=sake存入到session域对象中
public class AnnoController {

    @RequestMapping("/requestParam")
    public String testRequestParam(@RequestParam(name = "name") String username){
        System.out.println("testRequestParam执行了..");
        System.out.println("username:"+username);
        return "system/success";
    }

    /**
     * 获取到请求体的内容
     * 前台表单数据提交之后获取每一项值
     * 只能用于post
     * @return
     */
    @RequestMapping("/requestBody")
    public String testRequestBody(@RequestBody String body){
        System.out.println("testRequestBody执行了..");
        System.out.println(body);
        return "system/success";
    }

    /**
     * Pathvariable注解
     * @return
     */
    @RequestMapping("/pathVariable/{sid}")
    public String testPathVariable(@PathVariable(value = "sid") String id){
        System.out.println("testPathVariable执行了..");
        System.out.println(id);
        return "system/success";
    }


    /**
     *获取请求头的信息
     * @return
     */
    @RequestMapping("/requestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept") String header){
        System.out.println("testRequestHeader执行了...");
        System.out.println("请求头："+header);
        return "system/success";
    }

    /**
     * 获取Cookie的值
     * @return
     */
    @RequestMapping("/cookievalue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String cookieValue){
        System.out.println("testCookieValue执行了...");
        System.out.println("JSESSIONID:"+cookieValue);
        return "system/success";
    }

    /**
     * ModelAttribute注解
     * 当前台的表单提交过来和后端的domain不能对应时，可以采取ModelAttribute注解方法
     * eg：前台表单User{uname，age}，后台User{uname,age,date}。
     * 由于@ModelAttribute标注的函数会在映射后最先执行，因此可以对前台的数据进行初始化
     * @return
     */
    @RequestMapping("/modelAttribute")
    public String testModelAttribute(User user){
        System.out.println("testModelAttribute执行了..");
        System.out.println(user);
        return "system/success";
    }

    /**
     * 该方法会先执行
     */
    @ModelAttribute
    public User showUser(String uname){
        System.out.println("showUser执行了...");
        //通过用户名查询数据库（模拟）
        User user=new User();
        user.setUname(uname);
        user.setAge(20);
        user.setDate(new Date());
        return user;
    }

    /**
     * SessionAttributes注解
     * @return
     */
    @RequestMapping("/sessionAttributes")
    public String testSessionAttributes(Model model){
        System.out.println("sessionAttributes执行了...");
        //底层会存储到request域对象中
        model.addAttribute("msg","sake");
        //使用@SessionAttributes将request域对象中的信息存到session域对象中
        return "system/success";
    }

    /**
     * 获取session
     * @param modelMap
     * @return
     */
    @RequestMapping("/getSessionAttributes")
    public String getSessionAttributes(ModelMap modelMap){
        System.out.println("getSessionAttributes执行了...");
        String msg = (String) modelMap.get("msg");
        System.out.println("msg:"+msg);
        return "system/success";
    }

    @RequestMapping("/delSessionAttributes")
    public String delSessionAttributes(SessionStatus status){
        System.out.println("delSessionAttributes执行了...");
        status.setComplete();
        System.out.println("session域被清除了！");
        return "system/success";
    }
}
