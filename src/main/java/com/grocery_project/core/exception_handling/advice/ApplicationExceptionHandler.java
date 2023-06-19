package com.grocery_project.core.exception_handling.advice;

import com.grocery_project.core.base.BaseResponse;
import com.grocery_project.core.exception_handling.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<BaseResponse> commentException1(Exception exception) {
        exception.printStackTrace();
        return null;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<List<String>> handleInvalidArgument(MethodArgumentNotValidException ex) {

        List<String> errorMap = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.add(error.getField() + ": " + error.getDefaultMessage());
        });

        return new BaseResponse<>(errorMap, HttpStatus.BAD_REQUEST.name(), Boolean.FALSE, HttpStatus.BAD_REQUEST.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<List<String>> handleRuntimeException(RuntimeException ex) {

//        logger.info(ex.toString());
//        logger.info("===========================================");
//        logger.info(ex.getMessage());
//        logger.info("===========================================");
//        logger.info(ex.getCause().toString());
//        logger.info("===========================================");
//        logger.info(ex.getStackTrace().toString());
//        logger.info("===========================================");
//        logger.info(ex.getSuppressed().toString());
//        logger.info("===========================================");
//        logger.info(ex.getLocalizedMessage());
//        logger.info("===========================================");
//        logger.info(ex.getClass().toString());
//        logger.info("===========================================");
//        ex.printStackTrace();
//        logger.info("===========================================");

        return new BaseResponse<>(Collections.singletonList(ex.getMessage()), HttpStatus.BAD_REQUEST.name(), Boolean.FALSE, HttpStatus.BAD_REQUEST.value());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public BaseResponse<List<String>> handleInternalServerErrorException(HttpServerErrorException.InternalServerError ex) {
        return new BaseResponse<>(Collections.singletonList(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR.name(), Boolean.FALSE, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateRecordException.class)
    public BaseResponse<List<String>> handlerDuplicateRecordException(DuplicateRecordException ex) {

        return new BaseResponse<>(Collections.singletonList(ex.getMessage()), HttpStatus.CONFLICT.name(), Boolean.FALSE, HttpStatus.CONFLICT.value());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    public BaseResponse<List<String>> handlerRecordNotFound(RecordNotFoundException ex) {

        return new BaseResponse<>(Collections.singletonList(ex.getMessage()), HttpStatus.NOT_FOUND.name(), Boolean.FALSE, HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidDataEntryException.class)
    public BaseResponse<List<String>> handlerInvalidDataEntry(InvalidDataEntryException ex) {

        return new BaseResponse<>(Collections.singletonList(ex.getMessage()), HttpStatus.BAD_REQUEST.name(), Boolean.FALSE, HttpStatus.BAD_REQUEST.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    public BaseResponse<List<String>> usernameNotFoundException(UsernameNotFoundException ex) {

        return new BaseResponse<>(Collections.singletonList(ex.getMessage()), HttpStatus.BAD_REQUEST.name(), Boolean.FALSE, HttpStatus.BAD_REQUEST.value());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(CustomAccessDeniedHandler.class)
    public BaseResponse<List<String>> forbiddenException(CustomAccessDeniedHandler ex) {

        return new BaseResponse<>(Collections.singletonList(ex.getMessage()), HttpStatus.FORBIDDEN.name(), Boolean.FALSE, HttpStatus.FORBIDDEN.value());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(CustomExpiredJwtException.class)
    public BaseResponse<List<String>> forbiddenException(CustomExpiredJwtException ex) {

        return new BaseResponse<>(Collections.singletonList(ex.getMessage()), HttpStatus.FORBIDDEN.name(), Boolean.FALSE, HttpStatus.FORBIDDEN.value());
    }


//    @ExceptionHandler(value = {Exception.class})
//    public ResponseEntity<BaseResponse> commentException(Exception exception) {
//        exception.printStackTrace();
//        return null;
//    }


}
