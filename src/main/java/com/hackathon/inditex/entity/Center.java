package com.hackathon.inditex.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
