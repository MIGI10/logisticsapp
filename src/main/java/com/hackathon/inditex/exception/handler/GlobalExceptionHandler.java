package com.hackathon.inditex.exception.handler;

import com.hackathon.inditex.dto.MessageResponseDTO;
import com.hackathon.inditex.exception.CenterAlreadyExistsException;
import com.hackathon.inditex.exception.CenterExceedsCapacityException;
import com.hackathon.inditex.exception.CenterNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CenterAlreadyExistsException.class)
    public ResponseEntity<MessageResponseDTO> handleCenterAlreadyExists(CenterAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponseDTO(e.getMessage()));
    }

    @ExceptionHandler(CenterExceedsCapacityException.class)
    public ResponseEntity<MessageResponseDTO> handleCenterExceedsCapacity(CenterExceedsCapacityException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponseDTO(e.getMessage()));
    }

    @ExceptionHandler(CenterNotFoundException.class)
    public ResponseEntity<MessageResponseDTO> handleCenterNotFound(CenterNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponseDTO(e.getMessage()));
    }
}