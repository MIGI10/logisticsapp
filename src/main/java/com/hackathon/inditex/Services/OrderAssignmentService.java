package com.hackathon.inditex.Services;

import com.hackathon.inditex.DTO.ProcessedOrderDTO;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Service for Order Assignment
 */
public interface OrderAssignmentService {
    @Transactional
    Map<String, List<ProcessedOrderDTO>> assignPendingOrders();
}
