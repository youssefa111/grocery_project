package com.grocery_project.core.base;

import lombok.Data;

import java.util.List;


@Data
public class BaseResponse<T> {
    private String message ;
    private int statusCode;
    private T body;

   public BaseResponse(){
    message = "SUCCESS";
    statusCode = 200;
    }

    public BaseResponse(T body){
        message = "SUCCESS";
        statusCode = 200;
        this.body = body;
    }
//    public BaseResponse(String message){
//        this.message = message;
//        statusCode = 200;
//    }
    public BaseResponse(T body ,String message){
       this.message = message;
        statusCode = 200;
        this.body = body;
    }
}
