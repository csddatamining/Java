package com.study.springboot.intercept;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Cdu
 * @discription:拦截器
 * @create 2020-07-05 0:34
 */
public class LoginHandlerIntercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object username = request.getSession().getAttribute("username");
        if (username != null) {
            return true;
        } else {
            request.setAttribute("login_error", "请先登录");
            request.getRequestDispatcher("/").forward(request, response);
            return false;
        }
    }
}