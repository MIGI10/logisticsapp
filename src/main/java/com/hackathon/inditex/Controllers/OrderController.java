package com.hackathon.inditex.Controllers;

import com.hackathon.inditex.DTO.CreateOrderResponseDTO;
import com.hackathon.inditex.DTO.OrderDTO;
import com.hackathon.inditex.DTO.ProcessedOrderDTO;
import com.hackathon.inditex.Services.OrderAssignmentService;
import com.hackathon.inditex.Services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller class for the Order entity.
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderAssignmentService orderAssignmentService;

    /**
     * Creates a new order.
     * @param order The order to be created.
     * @return A response with the created order.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponseDTO createOrder(@RequestBody OrderDTO order) {
        return orderService.createOrder(order);
    }

    /**
     * Retrieves all orders.
     * @return A list of all orders.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    /**
     * Assigns pending orders to logistics centers.
     * @return A map with the assigned orders.
     */
    @PostMapping("/order-assignations")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, List<ProcessedOrderDTO>> assignOrders() {
        return orderAssignmentService.assignPendingOrders();
    }
}
