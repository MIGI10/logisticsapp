package com.hackathon.inditex.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProcessedOrderDTO {
    private Long orderId;
    private Double distance;
    private String assignedLogisticsCenter;
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
}
