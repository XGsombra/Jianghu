package com.jianghu.jianghu.mapper;

import com.jianghu.jianghu.entity.UserAuth;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAuthMapper {

    Boolean existUserAuthTable();

    void createUserAuthTable();

    void createUserCredential(String userId, String salt, String hash);

    UserAuth getCredentialByUserId(String userId);
}
