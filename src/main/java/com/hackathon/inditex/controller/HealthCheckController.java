package com.hackathon.inditex.controller;

import com.hackathon.inditex.constant.Messages;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for the health check endpoint.
 */
@RestController
@RequestMapping("/health")
public class HealthCheckController {

    /**
     * Health check endpoint.
     * @return A message indicating that the API is working.
     */
    @GetMapping
    public String healthCheck() {
        return Messages.API_WORKING;
    }
}