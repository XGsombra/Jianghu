package com.jianghu.jianghu.Entity;

import java.io.Serializable;

public class UserAuth implements Serializable {

    private String userId;
    private String salt;
    private String hash;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSalt() {
        return salt;
    }

    public String getHash() {
        return hash;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setSaltedHash(String hash) {
        this.hash = hash;
    }
}
