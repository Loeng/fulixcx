package com.gys.fulixcx.util;

import org.json.JSONObject;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class UserConfig extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println(handler.getClass());
        if (!isGysAnnoation(handler)) {
            if (request.getSession().getAttribute("sessionMode") != null) {
                return true;
            } else {
                String requestType = request.getHeader("X-Requested-With");
                if ("XMLHttpRequest".equals(requestType)) {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("text/html; charset=utf-8");
                    PrintWriter writer = response.getWriter();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("code", 500);
                    jsonObject.put("msg", "请登录");
                    writer.write(jsonObject.toString());
                } else {
                    request.getRequestDispatcher("/").forward(request, response);
                    //此时requestType为null
                }
                return false;
            }
        }
        return true;
    }

    private boolean isGysAnnoation(Object handler) {
        try {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Object bean = handlerMethod.getBean();
            if (bean.getClass().isAnnotationPresent(GysAnnotation.class)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
