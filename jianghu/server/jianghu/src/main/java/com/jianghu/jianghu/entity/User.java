package com.jianghu.jianghu.entity;

import java.io.Serializable;

public class User implements Serializable{

    private String userId;
    private String username;
    private String email;
    private String phone;
    private Integer level;

    public User(String userId, String username, String email, String phone){
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.level = 0;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public Integer getLevel() {
        return level;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
