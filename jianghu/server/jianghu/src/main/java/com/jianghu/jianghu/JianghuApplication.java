package com.jianghu.jianghu;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

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
