package com.hackathon.inditex.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class for Coordinates
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesDTO {
    private Double latitude;
    private Double longitude;
}
