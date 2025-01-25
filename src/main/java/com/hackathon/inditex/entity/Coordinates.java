package com.hackathon.inditex.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class for Coordinates
 */
@Embeddable
@Getter
@Setter
public class Coordinates {
    private double latitude;
    private double longitude;
}