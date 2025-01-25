package com.hackathon.inditex.service;

import com.hackathon.inditex.dto.ProcessedOrderDTO;
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
