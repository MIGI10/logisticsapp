package com.hackathon.inditex.mapper;

import com.hackathon.inditex.dto.CreateOrderResponseDTO;
import com.hackathon.inditex.dto.OrderDTO;
import com.hackathon.inditex.dto.ProcessedOrderDTO;
import com.hackathon.inditex.entity.Order;

import java.util.List;

public interface OrderMapper {

    Order toOrder(OrderDTO dto);

    CreateOrderResponseDTO toCreateOrderResponseDTO(Order order);

    OrderDTO toOrderDTO(Order order);

    List<OrderDTO> toOrderDTOList(List<Order> orders);

    ProcessedOrderDTO toProcessedOrderDTO(Order order);
}
