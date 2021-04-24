package com.jianghu.jianghu.dao;

import com.jianghu.jianghu.Entity.UserAuth;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserAuthDao {

    @Select("SELECT COUNT(*) > 0\n" +
            "        FROM information_schema.TABLES\n" +
            "        WHERE table_name = 'user_auth'")
    Boolean existUserAuthTable();

    @Update("create table user_auth (\n" +
            "        userid TEXT NOT NULL,\n" +
            "        salt TEXT NOT NULL,\n" +
            "        hash TEXT NOT NULL,\n" +
            "        PRIMARY KEY (userid))")
    void createUserAuthTable();

    @Insert("INSERT INTO user_auth(userid, salt, hash)\n" +
            " VALUES(#{userId}, #{salt}, #{hash})")
    void createUserAuth(String userId, String salt, String hash);


    @Select("SELECT * FROM user_auth WHERE userid = #{userId}")
    UserAuth getCredentialByUserId(String userId);
}
