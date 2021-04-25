package com.jianghu.jianghu.middleware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

public class GeneralInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        InputStream inputStream = request.getInputStream();
        String uri = request.getRequestURI();
        String sessionId = request.getSession().getId();
        String method = request.getMethod();
        String ip = request.getRemoteUser();

        logger.info("接收到请求URL【{}】,请求的sessionId【{}】,请求的类型【{}】",uri,sessionId,method);
        return true;
    }
}
