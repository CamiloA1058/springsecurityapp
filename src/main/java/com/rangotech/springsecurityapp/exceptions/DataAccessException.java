package com.rangotech.springsecurityapp.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DataAccessException  extends RuntimeException{
    private final HttpStatus httpStatus;
    public DataAccessException(String msg, HttpStatus httpStatus){
        super(msg);
        this.httpStatus = httpStatus;
    }
}
