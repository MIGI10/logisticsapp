package com.hackathon.inditex.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class for Center
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CenterDTO {
    private Long id;
    private String name;
    private String capacity;
    private String status;
    private Integer maxCapacity;
    private Integer currentLoad;
    private CoordinatesDTO coordinates;
}
