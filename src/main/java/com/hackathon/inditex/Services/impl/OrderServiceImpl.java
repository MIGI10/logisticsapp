package com.hackathon.inditex.Services.impl;

import com.hackathon.inditex.DTO.CreateOrderResponseDTO;
import com.hackathon.inditex.DTO.OrderDTO;
import com.hackathon.inditex.Entities.Order;
import com.hackathon.inditex.Mappers.OrderMapper;
import com.hackathon.inditex.Repositories.OrderRepository;
import com.hackathon.inditex.Services.OrderService;
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
