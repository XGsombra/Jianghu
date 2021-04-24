package com.jianghu.jianghu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignInUserInputDto extends InputDto{

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("password")
    private String password;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Checks if the input DTO is valid
     *
     * @return true if the input is valid, false otherwise
     */
    @Override
    public Boolean check() {
        return this.userId != null && this.password != null;
    }
}
