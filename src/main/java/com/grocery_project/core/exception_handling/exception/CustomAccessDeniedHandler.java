package com.grocery_project.core.exception_handling.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDeniedHandler  extends RuntimeException{
    public CustomAccessDeniedHandler() {
        super();
    }

    public CustomAccessDeniedHandler(String message) {
        super(message);
    }
}
