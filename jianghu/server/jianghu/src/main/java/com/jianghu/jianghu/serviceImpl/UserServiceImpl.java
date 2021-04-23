package com.jianghu.jianghu.serviceImpl;

import com.jianghu.jianghu.Entity.User;
import com.jianghu.jianghu.mapper.UserMapper;
import com.jianghu.jianghu.service.AuthenticationService;
import com.jianghu.jianghu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService, AuthenticationService {

    @Autowired
    private UserMapper userMapper;

    /** Initialize the user_info table.
     *
     * @return true if initialized the user_info table, false if the table already exists
     */
    public Boolean initializeUserInfoTable(){
        if (!userMapper.existTable("user_info")){
            userMapper.createUserInfoTable();
            System.out.println("Successfully initiated tables for user information");
            return true;
        }
        System.out.println("user_info already exists");
        return false;
    }

    /** Add user information to the database.
     *
     * @param username username of the user
     * @param email email of the user
     * @param phone phone number of the user
     * @return the UUID of the user
     */
    @Override
    public String addUser(String username, String email, String phone) {
        String userId = UUID.randomUUID().toString();
        userMapper.createUserInfo(userId, username, email, phone, 0);
        return userId;
    }

    /**
     * Get the user information.
     *
     * @param userid the UUID of the user
     * @return the user object that represents the user information
     */
    @Override
    public User GetUser(String userid) {
        return null;
    }
    
    @Override
    public Boolean addUserCredential(String userId, String password) {
        return false;
    }

    @Override
    public Boolean authenticateUser(String userId, String password) {
        return null;
    }
}
