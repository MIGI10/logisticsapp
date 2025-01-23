package com.hackathon.inditex.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

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
