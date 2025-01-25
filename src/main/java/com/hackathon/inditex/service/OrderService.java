package com.hackathon.inditex.service;

import com.hackathon.inditex.dto.CreateOrderResponseDTO;
import com.hackathon.inditex.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    CreateOrderResponseDTO createOrder(OrderDTO order);
    List<OrderDTO> getAllOrders();
}
