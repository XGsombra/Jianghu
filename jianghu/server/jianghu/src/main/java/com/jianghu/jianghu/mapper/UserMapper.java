package com.jianghu.jianghu.mapper;

import com.jianghu.jianghu.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("SELECT COUNT(*) > 0\n" +
            "        FROM information_schema.TABLES\n" +
            "        WHERE table_name=#{tableName}")
    Boolean existTable(String tableName);

    @Update("create table user_info (\n" +
            "        userid TEXT NOT NULL,\n" +
            "        username TEXT NOT NULL,\n" +
            "        email TEXT,\n" +
            "        phone TEXT,\n" +
            "        level INT NOT NULL,\n" +
            "        PRIMARY KEY (userid))")
    void createUserInfoTable();

    @Insert("INSERT INTO user_info(userid, username, email, phone, level)\n" +
            " VALUES(#{userid}, #{username}, #{email}, #{phone}, #{level})")
    void createUserInfo(String userid, String username, String email, String phone, Integer level);

}
