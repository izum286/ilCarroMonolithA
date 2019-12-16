package com.telran.ilcarro.repository.exception;

import com.telran.ilcarro.repository.exception.RepositoryException;

public class ConflictRepositoryException extends RepositoryException {
    public ConflictRepositoryException(String message) {
        super(message);
    }

    public ConflictRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
