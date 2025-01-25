package com.hackathon.inditex.Exceptions.handler;

import com.hackathon.inditex.Constants.Messages;
import com.hackathon.inditex.DTO.MessageResponseDTO;
import com.hackathon.inditex.Exceptions.CenterAlreadyExistsException;
import com.hackathon.inditex.Exceptions.CenterExceedsCapacityException;
import com.hackathon.inditex.Exceptions.CenterNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the application
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles CenterAlreadyExistsException
     * @param e CenterAlreadyExistsException
     * @return ResponseEntity with the error message
     */
    @ExceptionHandler(CenterAlreadyExistsException.class)
    public ResponseEntity<MessageResponseDTO> handleCenterAlreadyExists(CenterAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponseDTO(e.getMessage()));
    }

    /**
     * Handles CenterExceedsCapacityException
     * @param e CenterExceedsCapacityException
     * @return ResponseEntity with the error message
     */
    @ExceptionHandler(CenterExceedsCapacityException.class)
    public ResponseEntity<MessageResponseDTO> handleCenterExceedsCapacity(CenterExceedsCapacityException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponseDTO(e.getMessage()));
    }

    /**
     * Handles CenterNotFoundException
     * @param e CenterNotFoundException
     * @return ResponseEntity with the error message
     */
    @ExceptionHandler(CenterNotFoundException.class)
    public ResponseEntity<MessageResponseDTO> handleCenterNotFound(CenterNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponseDTO(e.getMessage()));
    }

    /**
     * Handles DataIntegrityViolationException
     * @param ignored DataIntegrityViolationException
     * @return ResponseEntity with the error message
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MessageResponseDTO> handleDataIntegrityViolation(DataIntegrityViolationException ignored) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponseDTO(Messages.CENTER_IN_USE_ERR));
    }
}