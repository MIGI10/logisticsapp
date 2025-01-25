package com.hackathon.inditex.Services;

import com.hackathon.inditex.DTO.CreateOrderResponseDTO;
import com.hackathon.inditex.DTO.OrderDTO;

import java.util.List;

/**
 * Service for Order entity
 */
public interface OrderService {
    CreateOrderResponseDTO createOrder(OrderDTO order);
    List<OrderDTO> getAllOrders();
}
