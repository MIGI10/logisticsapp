package com.hackathon.inditex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO class for CreateOrderResponse
 */
@Data
@AllArgsConstructor
public class CreateOrderResponseDTO {
    private Long orderId;
    private Long customerId;
    private String size;
    private String assignedLogisticsCenter;
    private CoordinatesDTO coordinates;
    private String status;
    private String message;
}
