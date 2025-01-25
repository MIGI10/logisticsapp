package com.hackathon.inditex.Exceptions;

/**
 * Exception thrown when a center is not found in the database
 */
public class CenterNotFoundException extends RuntimeException {
    public CenterNotFoundException(String message) {
        super(message);
    }
}
