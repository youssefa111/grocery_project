package com.grocery_project.core.exception_handling.advice;

import com.grocery_project.core.exception_handling.error.ErrorModel;
import com.grocery_project.core.exception_handling.exception.DuplicateRecordException;
import com.grocery_project.core.exception_handling.exception.InvalidDataEntryException;
import com.grocery_project.core.exception_handling.exception.RecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorModel handleInvalidArgument(MethodArgumentNotValidException ex) {

       List<String> errorMap = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.add(error.getField() + ": " + error.getDefaultMessage());
        });

        return new ErrorModel(HttpStatus.BAD_REQUEST.name(), errorMap);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ErrorModel handleRuntimeException(RuntimeException ex) {

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

        return new ErrorModel(HttpStatus.BAD_REQUEST.name(), Collections.singletonList(ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ErrorModel handleInternalServerErrorException(HttpServerErrorException.InternalServerError ex) {
        return new ErrorModel( HttpStatus.INTERNAL_SERVER_ERROR.name(), Collections.singletonList(ex.getMessage()));
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateRecordException.class)
    public ErrorModel handlerDuplicateRecordException(DuplicateRecordException ex){

        return new ErrorModel(HttpStatus.CONFLICT.name(), Collections.singletonList(ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    public ErrorModel handlerRecordNotFound(RecordNotFoundException ex){

        return new ErrorModel(HttpStatus.NOT_FOUND.name(), Collections.singletonList(ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidDataEntryException.class)
    public ErrorModel handlerInvalidDataEntry(InvalidDataEntryException ex){

        return new ErrorModel(HttpStatus.BAD_REQUEST.name(), Collections.singletonList(ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ErrorModel usernameNotFoundException(UsernameNotFoundException ex){

        return new ErrorModel(HttpStatus.BAD_REQUEST.name(), Collections.singletonList(ex.getMessage()));
    }


}
