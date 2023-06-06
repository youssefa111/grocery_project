package com.grocery_project.core.exception_handling.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ErrorModel{

    private Boolean success;



    private String message;

    private LocalDateTime dateTime;

    private List<String> details;

    public ErrorModel(String message , List<String> details){
        this.message = message;
        this.details = details;
        this.success = Boolean.FALSE;
        this.dateTime = LocalDateTime.now();
    };

}
