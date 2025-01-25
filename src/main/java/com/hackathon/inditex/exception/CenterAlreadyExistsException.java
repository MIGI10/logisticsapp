package com.hackathon.inditex.exception;


/**
 * Exception thrown when a center already exists in the database
 */
public class CenterAlreadyExistsException extends RuntimeException {
    public CenterAlreadyExistsException(String message) {
            super(message);
    }
}