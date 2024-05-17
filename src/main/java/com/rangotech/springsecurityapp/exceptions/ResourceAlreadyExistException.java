package com.rangotech.springsecurityapp.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class ResourceAlreadyExistException extends DataIntegrityViolationException {
    public ResourceAlreadyExistException(String msg) {
        super(msg);
    }
}
