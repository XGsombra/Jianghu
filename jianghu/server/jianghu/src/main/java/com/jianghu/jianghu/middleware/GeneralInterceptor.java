package com.jianghu.jianghu.middleware;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class GeneralInterceptor implements HandlerInterceptor {

    @Value("${jianghu.files.http-log-location}")
    private String httpLogPath;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // log the http request
        StringBuffer logItem = new StringBuffer();
        // add the request time to log item
        logItem.append("request time: " + new Date() + "\n");
        // add the IP address to log item
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        logItem.append("IP address:   " + ipAddress + "\n");
        // add the http method to log item
        logItem.append("HTTP method:  " + request.getMethod() + "\n");
        // add the session id to log item
        logItem.append("session ID:   " + request.getSession().getId() + "\n");
        // add the user id to log item
        logItem.append("user ID:      " + request.getSession().getAttribute("userId") + "\n");
        // add the request path to log item
        logItem.append("request URI:  " + request.getRequestURI() + "\n");
        // add the separator to log item
        logItem.append("---------------------------------------------------\n");

        System.out.println(this.httpLogPath);
        //Path path = Paths.get(httpLogPath);
        return true;
    }
}
