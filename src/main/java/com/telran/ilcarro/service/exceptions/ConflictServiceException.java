package com.telran.ilcarro.service.exceptions;

public class ConflictServiceException extends ServiceException {
    public ConflictServiceException(String message) {
        super(message);
    }

    public ConflictServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
