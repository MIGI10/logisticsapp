package com.hackathon.inditex.controller;

import com.hackathon.inditex.dto.CreateOrderResponseDTO;
import com.hackathon.inditex.dto.OrderDTO;
import com.hackathon.inditex.dto.ProcessedOrderDTO;
import com.hackathon.inditex.service.OrderAssignmentService;
import com.hackathon.inditex.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderAssignmentService orderAssignmentService;

    @Autowired
    public OrderController(OrderService orderService, OrderAssignmentService orderAssignmentService) {
        this.orderService = orderService;
        this.orderAssignmentService = orderAssignmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponseDTO createOrder(@RequestBody OrderDTO order) {
        return orderService.createOrder(order);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/order-assignations")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, List<ProcessedOrderDTO>> assignOrders() {
        return orderAssignmentService.assignPendingOrders();
    }
}
