package com.rangotech.springsecurityapp.exceptions;

import org.springframework.http.HttpStatus;


public class UserNotFoundException extends DataAccessException{
    public UserNotFoundException(String msg, HttpStatus httpStatus){
        super(msg, httpStatus);
    }
}
