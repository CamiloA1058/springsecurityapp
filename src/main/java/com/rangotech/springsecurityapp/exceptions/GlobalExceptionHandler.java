package com.rangotech.springsecurityapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<ApiResponse> myUserNotFoundException(UserNotFoundException ex) {
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceAlreadyExistException.class)
    protected ResponseEntity<ApiResponse> myUserNotFoundException(ResourceAlreadyExistException ex) {
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ApiResponse> myResourceNotFoundException(ResourceNotFoundException ex) {
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<ApiResponse> myResourceNotFoundException(ApiException ex) {
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}