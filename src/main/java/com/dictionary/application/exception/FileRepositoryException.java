package com.dictionary.application.exception;

public class FileRepositoryException extends RuntimeException {
    public FileRepositoryException(String message, Exception e) {
        super(message, e);
    }
}
