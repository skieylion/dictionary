package com.dictionary.core.exception;

public class FileRepositoryException extends RuntimeException {
    public FileRepositoryException(String message, Exception e) {
        super(message, e);
    }
}
