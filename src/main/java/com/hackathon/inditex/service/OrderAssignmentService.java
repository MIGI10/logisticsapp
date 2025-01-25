package com.hackathon.inditex.service;

import com.hackathon.inditex.dto.ProcessedOrderDTO;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Map;

public interface OrderAssignmentService {
    @Transactional
    Map<String, List<ProcessedOrderDTO>> assignPendingOrders();
}
