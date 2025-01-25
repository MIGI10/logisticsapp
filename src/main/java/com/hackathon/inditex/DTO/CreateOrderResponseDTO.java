package com.hackathon.inditex.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class for CreateOrderResponse
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponseDTO {
    private Long orderId;
    private Long customerId;
    private String size;
    private String assignedLogisticsCenter;
    private CoordinatesDTO coordinates;
    private String status;
    private String message;
}
