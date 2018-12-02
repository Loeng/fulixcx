package com.gys.fulixcx.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserConfig extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println(handler.getClass());
        if(!isGysAnnoation(handler)){
            if (request.getSession().getAttribute("sessionMode")!=null){
                return true;
            }else {
                String requestType = request.getHeader("X-Requested-With");
                if("XMLHttpRequest".equals(requestType)){
                    System.out.println("AJAX请求..");
                }else{
                    System.out.println("非AJAX请求..");
                    //此时requestType为null
                }
                response.sendRedirect(request.getContextPath()+"");
                return false;
            }
        }
        return true;
    }

    private boolean isGysAnnoation(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Object bean = handlerMethod.getBean();
        if(bean.getClass().isAnnotationPresent(GysAnnotation.class)){
            return false;
        }
        return true;
    }
}
