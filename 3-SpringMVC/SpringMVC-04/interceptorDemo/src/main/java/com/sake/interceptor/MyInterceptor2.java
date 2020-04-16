package com.sake.interceptor;

import com.sake.controller.UserController;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 自定义拦截器
 */
public class MyInterceptor2 implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(UserController.class);

    /**
     * 预处理：controller方法执行前
     * return true 放行，执行下一个拦截器，如果没有，执行controller中的方法
     * return false 不放行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("拦截器2执行了...前");
        return true;
        //request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request,response);
        //logger.debug("未放行，重定向到error.jsp页面了");
        //return false;
    }

    /**
     * 后处理：cotroller方法执行后，success.jsp执行之前
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.debug("拦截器2执行了...后");
        //request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request,response);
        //logger.debug("未放行，重定向到error.jsp页面了");


    }


    /**
     * success.jsp页面执行后，该方法会执行
     * @param request
     * @param response
     * @param handler
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
        logger.debug("拦截器2执行了...最后");
    }
}
