package com.jianghu.jianghu.middleware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (uri.contains("api/users/login") || uri.contains("/signup")) {
            return true;
        }
        if (request.getSession().getAttribute("userId") == null) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(403);
            response.getWriter().print("{\n    message: \"Please log in first\"\n}");
            return false;
        }
        return true;
    }

    // method src: https://blog.csdn.net/qq_42055933/article/details/104572883
    private boolean isAjax(HttpServletRequest request) {
        String xrw = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(xrw)) {
            return true;
        }
        return false;
    }
}
