package com.hackathon.inditex.Exceptions;

/**
 * Exception thrown when a center exceeds its capacity
 */
public class CenterExceedsCapacityException extends RuntimeException {
    public CenterExceedsCapacityException(String message) {
        super(message);
    }
}
