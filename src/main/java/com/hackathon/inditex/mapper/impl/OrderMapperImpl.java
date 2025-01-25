package com.hackathon.inditex.mapper.impl;

import com.hackathon.inditex.dto.CoordinatesDTO;
import com.hackathon.inditex.dto.CreateOrderResponseDTO;
import com.hackathon.inditex.dto.OrderDTO;
import com.hackathon.inditex.dto.ProcessedOrderDTO;
import com.hackathon.inditex.entity.Coordinates;
import com.hackathon.inditex.entity.Order;
import com.hackathon.inditex.mapper.OrderMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toOrder(OrderDTO dto) {
        if (dto == null) {
            return null;
        }

        Order order = new Order();
        order.setCustomerId(dto.getCustomerId());
        order.setSize(dto.getSize());

        if (dto.getCoordinates() != null) {
            Coordinates coordinates = new Coordinates();
            coordinates.setLatitude(dto.getCoordinates().getLatitude());
            coordinates.setLongitude(dto.getCoordinates().getLongitude());
            order.setCoordinates(coordinates);
        }

        return order;
    }

    @Override
    public CreateOrderResponseDTO toCreateOrderResponseDTO(Order order) {
        if (order == null) {
            return null;
        }

        CoordinatesDTO coordinatesDTO = null;
        if (order.getCoordinates() != null) {
            coordinatesDTO = new CoordinatesDTO(
                    order.getCoordinates().getLatitude(),
                    order.getCoordinates().getLongitude()
            );
        }

        return new CreateOrderResponseDTO(
                order.getId(),
                order.getCustomerId(),
                order.getSize(),
                order.getAssignedCenterName(),
                coordinatesDTO,
                order.getStatus(),
                "Order created successfully in PENDING status."
        );
    }

    @Override
    public OrderDTO toOrderDTO(Order order) {
        if (order == null) {
            return null;
        }

        CoordinatesDTO coordinatesDTO = null;
        if (order.getCoordinates() != null) {
            coordinatesDTO = new CoordinatesDTO(
                    order.getCoordinates().getLatitude(),
                    order.getCoordinates().getLongitude()
            );
        }

        return new OrderDTO(
                order.getId(),
                order.getCustomerId(),
                order.getSize(),
                order.getStatus(),
                order.getAssignedCenterName(),
                coordinatesDTO
        );
    }

    @Override
    public List<OrderDTO> toOrderDTOList(List<Order> orders) {
        if (orders == null) {
            return null;
        }

        return orders.stream()
                .map(this::toOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProcessedOrderDTO toProcessedOrderDTO(Order order) {
        if (order == null) {
            return null;
        }
        ProcessedOrderDTO dto = new ProcessedOrderDTO();
        dto.setOrderId(order.getId());
        dto.setStatus(order.getStatus());
        return dto;
    }
}
