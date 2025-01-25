package com.hackathon.inditex.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity class for Order
 */
@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private String size;
    private String status;

    @Embedded
    private Coordinates coordinates;

    @ManyToOne
    @JsonIgnore
    private Center assignedCenter;

    public Order() {
        this.status = "PENDING";
    }

    @JsonProperty("assignedLogisticsCenter")
    public String getAssignedCenterName() {
        return assignedCenter == null ? null : assignedCenter.getName();
    }
}
