package com.jianghu.jianghu;

import com.jianghu.jianghu.serviceImpl.TaskServiceImpl;
import com.jianghu.jianghu.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements InitializingBean {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private TaskServiceImpl taskServiceImpl;

    @Override
    public void afterPropertiesSet() throws Exception {
        userServiceImpl.initializeUserInfoTable(); // initialize user_info table
        userServiceImpl.initializeUserAuthTable(); // initialize user_auth table
        taskServiceImpl.initializeTaskTable(); // initialize task table
    }
}
