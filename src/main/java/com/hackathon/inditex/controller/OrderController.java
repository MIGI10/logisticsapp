package com.hackathon.inditex.controller;

import com.hackathon.inditex.dto.CoordinatesDTO;
import com.hackathon.inditex.dto.CreateOrderResponseDTO;
import com.hackathon.inditex.dto.OrderDTO;
import com.hackathon.inditex.dto.ProcessedOrderDTO;
import com.hackathon.inditex.entity.Order;
import com.hackathon.inditex.service.OrderAssignmentService;
import com.hackathon.inditex.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public CreateOrderResponseDTO createOrder(@RequestBody Order order) {

        Order created = orderService.createOrder(order);

        CoordinatesDTO coordinatesDTO = new CoordinatesDTO(
                created.getCoordinates() != null ? created.getCoordinates().getLatitude() : null,
                created.getCoordinates() != null ? created.getCoordinates().getLongitude() : null
        );

        return new CreateOrderResponseDTO(
                created.getId(),
                created.getCustomerId(),
                created.getSize(),
                created.getAssignedCenterName(),
                coordinatesDTO,
                created.getStatus(),
                "Order created successfully in PENDING status."
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> getAllOrders() {

        List<Order> orders = orderService.getAllOrders();

        return orders.stream().map(order -> {
            CoordinatesDTO coordsDTO = new CoordinatesDTO(
                    order.getCoordinates() != null ? order.getCoordinates().getLatitude() : null,
                    order.getCoordinates() != null ? order.getCoordinates().getLongitude() : null
            );

            return new OrderDTO(
                    order.getId(),
                    order.getCustomerId(),
                    order.getSize(),
                    order.getStatus(),
                    order.getAssignedCenterName(),
                    coordsDTO
            );
        }).collect(Collectors.toList());
    }

    @PostMapping("/order-assignations")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, List<ProcessedOrderDTO>> assignOrders() {
        return orderAssignmentService.assignPendingOrders();
    }
}
