package com.grocery_project.core.exception_handling.exception;

public class CustomExpiredJwtException  extends RuntimeException{
    public CustomExpiredJwtException() {
        super();
    }

    public CustomExpiredJwtException(String message) {
        super(message);
    }
}