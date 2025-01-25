package com.hackathon.inditex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO class for Order
 */
@Data
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private Long customerId;
    private String size;
    private String status;
    private String assignedCenter;
    private CoordinatesDTO coordinates;
}
