package com.jianghu.jianghu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddUserInputDto {

    @JsonProperty("name")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
