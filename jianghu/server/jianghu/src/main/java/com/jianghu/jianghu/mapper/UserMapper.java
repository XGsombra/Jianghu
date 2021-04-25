package com.jianghu.jianghu.mapper;

import com.jianghu.jianghu.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getAllUsers();

    Boolean existUserInfoTable();

    void createUserInfoTable();

    void createUserInfo(String userId, String username, String email, String phone, Integer level);

    void deleteUserInfo(String userId);

    User getUserByUserId(String userId);

    String getUserIdByEmail(String email);

    String getUserIdByPhone(String phone);
}
