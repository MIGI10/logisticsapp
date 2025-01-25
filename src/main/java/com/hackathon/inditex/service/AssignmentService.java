package com.hackathon.inditex.service;

import com.hackathon.inditex.dto.ProcessedOrderDTO;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Map;

public interface AssignmentService {
    @Transactional
    Map<String, List<ProcessedOrderDTO>> assignPendingOrders();
}
