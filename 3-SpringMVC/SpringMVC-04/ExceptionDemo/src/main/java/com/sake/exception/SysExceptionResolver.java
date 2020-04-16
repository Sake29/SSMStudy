package com.sake.exception;



import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器
 */
public class SysExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger =org.apache.log4j.Logger.getLogger(SysExceptionResolver.class);

    /**
     *处理异常业务逻辑
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        logger.debug("resolveException执行了...");
        logger.debug("错误信息："+ ex.getMessage());
        //获取异常对象
        SysException e =null;
        if (ex instanceof SysException){
            e=(SysException) ex;
        }else{
            e = new SysException("系统正在维护");
        }
        //创建modelandview对象
        ModelAndView mav= new ModelAndView();
        mav.addObject("errorMsg",e.getMessage());
        mav.setViewName("error");
        return mav;
    }
}
