package com.hackathon.inditex.controller;

import com.hackathon.inditex.entity.LogisticsCenter;
import com.hackathon.inditex.service.LogisticsCenterService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/centers")
public class LogisticsCenterController {

    private final LogisticsCenterService service;

    @Autowired
    public LogisticsCenterController(LogisticsCenterService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createCenter(@RequestBody LogisticsCenter center) {
        try {
            service.createCenter(center);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of("message", "Logistics center created successfully."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<LogisticsCenter>> getAllCenters() {
        return ResponseEntity.ok(service.getAllCenters());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCenter(@PathVariable Long id, @RequestBody LogisticsCenter center) {
        try {
            service.updateCenter(id, center);
            return ResponseEntity.ok(Map.of("message", "Logistics center updated successfully."));
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", e.getMessage()));
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCenter(@PathVariable Long id) {
        service.deleteCenter(id);
        return ResponseEntity.ok(Map.of("message", "Logistics center deleted successfully."));
    }
}
