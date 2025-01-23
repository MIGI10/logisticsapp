package com.hackathon.inditex.Controllers;

import com.hackathon.inditex.DTO.CoordinatesDTO;
import com.hackathon.inditex.DTO.OrderCreateResponseDTO;
import com.hackathon.inditex.DTO.OrderDTO;
import com.hackathon.inditex.Entities.Order;
import com.hackathon.inditex.Services.AssignmentService;
import com.hackathon.inditex.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final AssignmentService assignmentService;

    @Autowired
    public OrderController(OrderService orderService, AssignmentService assignmentService) {
        this.orderService = orderService;
        this.assignmentService = assignmentService;
    }

    @PostMapping
    public ResponseEntity<OrderCreateResponseDTO> createOrder(@RequestBody Order order) {

        Order created = orderService.createOrder(order);

        CoordinatesDTO coordinatesDTO = new CoordinatesDTO(
                created.getCoordinates() != null ? created.getCoordinates().getLatitude() : null,
                created.getCoordinates() != null ? created.getCoordinates().getLongitude() : null
        );

        OrderCreateResponseDTO response = new OrderCreateResponseDTO(
                created.getId(),
                created.getCustomerId(),
                created.getSize(),
                created.getAssignedCenterName(),
                coordinatesDTO,
                created.getStatus(),
                "Order created successfully in PENDING status."
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();

        List<OrderDTO> responseList = orders.stream().map(order -> {
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

        return ResponseEntity.ok(responseList);
    }

    @PostMapping("/order-assignations")
    public ResponseEntity<?> assignOrders() {
        return ResponseEntity.ok(assignmentService.assignPendingOrders());
    }
}
