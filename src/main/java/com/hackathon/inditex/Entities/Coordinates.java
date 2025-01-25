package com.hackathon.inditex.Entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * Entity class for Coordinates
 */
@Embeddable
@Data
public class Coordinates {
    private double latitude;
    private double longitude;
}