package com.jianghu.jianghu;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import com.jianghu.jianghu.serviceImpl.UserServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@ComponentScan("com.jianghu.jianghu")
@EntityScan("com.jianghu.jianghu.entity")
@CrossOrigin
@EnableRedisHttpSession
@SpringBootApplication
public class JianghuApplication {

	public static void main(String[] args) {
		SpringApplication.run(JianghuApplication.class, args);
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver() {
			@Override
			public boolean isMultipart(HttpServletRequest request) {
				String method = request.getMethod().toLowerCase();
				if (!Arrays.asList("put", "post").contains(method)) {
					return false;
				}
				String contentType = request.getContentType();
				return (contentType != null && contentType.toLowerCase().startsWith("multipart/"));
			}
		};
	}

}
