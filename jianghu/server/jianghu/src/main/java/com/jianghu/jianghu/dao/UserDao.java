package com.jianghu.jianghu.dao;

import com.jianghu.jianghu.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDao {

    @Select("SELECT COUNT(*) > 0\n" +
            "        FROM information_schema.TABLES\n" +
            "        WHERE table_name = 'user_info'")
    Boolean existUserInfoTable();

    @Update("create table user_info (\n" +
            "        userid TEXT NOT NULL,\n" +
            "        username TEXT NOT NULL,\n" +
            "        email TEXT,\n" +
            "        phone TEXT,\n" +
            "        level INT NOT NULL,\n" +
            "        PRIMARY KEY (userid))")
    void createUserInfoTable();

    @Insert("INSERT INTO user_info(userid, username, email, phone, level)\n" +
            " VALUES(#{userId}, #{username}, #{email}, #{phone}, #{level})")
    void createUserInfo(String userId, String username, String email, String phone, Integer level);


    @Select("SELECT * FROM user_info WHERE userid = #{userId}")
    User getUserByUserId(String userId);

    @Select("SELECT userid FROM user_info WHERE email = #{email}")
    String getUserIdByEmail(String email);

    @Select("SELECT userid FROM user_info WHERE phone = #{phone}")
    String getUserIdByPhone(String phone);
}
