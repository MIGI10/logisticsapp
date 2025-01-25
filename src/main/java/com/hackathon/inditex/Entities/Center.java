package com.hackathon.inditex.Entities;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity class for Logistics Center
 */
@Entity
@Data
@Table(name = "centers", uniqueConstraints = @UniqueConstraint(columnNames = {"latitude", "longitude"}))
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String capacity;
    private String status;
    private Integer maxCapacity;
    private Integer currentLoad;
    @Embedded
    private Coordinates coordinates;
}
