package com.hackathon.inditex.exception;

/**
 * Exception thrown when a center is not found in the database
 */
public class CenterNotFoundException extends RuntimeException {

    public CenterNotFoundException(String message) {
        super(message);
    }
}
