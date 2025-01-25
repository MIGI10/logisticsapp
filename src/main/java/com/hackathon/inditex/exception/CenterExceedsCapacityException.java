package com.hackathon.inditex.exception;

/**
 * Exception thrown when a center exceeds its capacity
 */
public class CenterExceedsCapacityException extends RuntimeException {

    public CenterExceedsCapacityException(String message) {
        super(message);
    }
}
