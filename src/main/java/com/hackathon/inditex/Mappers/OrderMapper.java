package com.hackathon.inditex.Mappers;

import com.hackathon.inditex.DTO.CreateOrderResponseDTO;
import com.hackathon.inditex.DTO.OrderDTO;
import com.hackathon.inditex.DTO.ProcessedOrderDTO;
import com.hackathon.inditex.Entities.Order;

import java.util.List;

/**
 * Mapper for Order entity
 */
public interface OrderMapper {

    Order toOrder(OrderDTO dto);

    CreateOrderResponseDTO toCreateOrderResponseDTO(Order order);

    OrderDTO toOrderDTO(Order order);

    List<OrderDTO> toOrderDTOList(List<Order> orders);

    ProcessedOrderDTO toProcessedOrderDTO(Order order);
}
