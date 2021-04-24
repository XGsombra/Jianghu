package com.jianghu.jianghu.dto;

public class GetUserIdOutput {

    private String userId;

    public GetUserIdOutput(String userId){
        this.userId = userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
