package com.telran.ilcarro.repository.exception;

import java.util.ArrayList;

@Deprecated
public class RepositoryException extends RuntimeException {
    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}



