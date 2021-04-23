package com.jianghu.jianghu.dto;

public class HttpExceptionOutputDto {

    private String message;

    public HttpExceptionOutputDto(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
