package com.example.lmageeach.util;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断用户是否已经登录
        if (!isLoggedIn(request)) {
            // 未登录，返回错误信息给前端
            response.getWriter().write("未登录或登录已过期");
            return false; // 中断后续请求
        }

        // 用户已登录，允许继续执行后续请求
        return true;
    }

    // 判断用户是否已经登录
    private boolean isLoggedIn(HttpServletRequest request) {
        // 从 session 或 token 缓存中获取登录状态信息
        String token = (String) request.getSession().getAttribute("token");

        // 根据业务逻辑判断 token 是否有效
        return token != null && !token.isEmpty();
    }

}
