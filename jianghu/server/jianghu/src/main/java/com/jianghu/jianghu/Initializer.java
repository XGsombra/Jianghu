package com.jianghu.jianghu;

import com.jianghu.jianghu.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements InitializingBean {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public void afterPropertiesSet() throws Exception {
        userServiceImpl.initializeUserInfoTable(); //initialize user_info table
    }
}