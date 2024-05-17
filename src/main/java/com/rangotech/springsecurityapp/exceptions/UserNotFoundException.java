package com.rangotech.springsecurityapp.exceptions;


public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String msg){
        super(msg);
    }
}
