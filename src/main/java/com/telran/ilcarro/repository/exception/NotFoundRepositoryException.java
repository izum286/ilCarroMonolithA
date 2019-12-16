package com.telran.ilcarro.repository.exception;

public class NotFoundRepositoryException extends RepositoryException {

    public NotFoundRepositoryException(String message) {
        super(message);
    }

    public NotFoundRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
