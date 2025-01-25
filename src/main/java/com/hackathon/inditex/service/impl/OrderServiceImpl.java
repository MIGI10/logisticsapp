package com.hackathon.inditex.service.impl;

import com.hackathon.inditex.dto.CreateOrderResponseDTO;
import com.hackathon.inditex.dto.OrderDTO;
import com.hackathon.inditex.Entities.Order;
import com.hackathon.inditex.mapper.OrderMapper;
import com.hackathon.inditex.repository.OrderRepository;
import com.hackathon.inditex.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for orders.
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    /**
     * Creates a new order.
     * @param orderRequest The order to create.
     * @return The created order.
     */
    @Override
    public CreateOrderResponseDTO createOrder(OrderDTO orderRequest) {
        Order order = orderMapper.toOrder(orderRequest);
        Order createdOrder = orderRepository.save(order);
        return orderMapper.toCreateOrderResponseDTO(createdOrder);
    }

    /**
     * Retrieves all orders.
     * @return A list of all orders.
     */
    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toOrderDTOList(orders);
    }
}
