package com.jianghu.jianghu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddUserInputDto extends InputDto{

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    private String phone;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public Boolean check() {
        if (this.username == null || this.password == null || (this.email == null && this.phone == null)){
            return false;
        }
        return true;
    }
}
