package com.jianghu.jianghu;

import com.jianghu.jianghu.middleware.GeneralInterceptor;
import com.jianghu.jianghu.middleware.SessionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class JianghuConfigurer implements WebMvcConfigurer {

    @Bean
    public GeneralInterceptor generalInterceptor() {
        return new GeneralInterceptor();
    }

    @Bean
    public SessionInterceptor sessionInterceptor() {
        return new SessionInterceptor();
    }

    // method src: https://blog.csdn.net/qq_42055933/article/details/104572883
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(generalInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**");
       registry.addInterceptor(sessionInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
