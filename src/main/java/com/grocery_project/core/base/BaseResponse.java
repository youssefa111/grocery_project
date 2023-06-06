package com.grocery_project.core.base;

import lombok.Data;

import java.util.List;


@Data
public class BaseResponse<T> {
    private String message ;
    private int statusCode;

    private List<T> body;

   public BaseResponse(){
    message = "SUCCESS";
    statusCode = 200;
    }

    public BaseResponse(List<T> body){
        message = "SUCCESS";
        statusCode = 200;
        this.body = body;
    }
}
