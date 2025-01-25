package com.hackathon.inditex.service;

import com.hackathon.inditex.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);

    List<Order> getAllOrders();
}
