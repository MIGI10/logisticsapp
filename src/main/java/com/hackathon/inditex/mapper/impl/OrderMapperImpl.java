package com.hackathon.inditex.mapper.impl;

import com.hackathon.inditex.constant.Messages;
import com.hackathon.inditex.dto.CoordinatesDTO;
import com.hackathon.inditex.dto.CreateOrderResponseDTO;
import com.hackathon.inditex.dto.OrderDTO;
import com.hackathon.inditex.dto.ProcessedOrderDTO;
import com.hackathon.inditex.Entities.Coordinates;
import com.hackathon.inditex.Entities.Order;
import com.hackathon.inditex.mapper.OrderMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the OrderMapper interface.
 */
@Component
public class OrderMapperImpl implements OrderMapper {

    /**
     * Converts an OrderDTO object to an Order object.
     *
     * @param dto the OrderDTO object to convert
     * @return the converted Order object
     */
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

    /**
     * Converts an Order object to a CreateOrderResponseDTO object.
     *
     * @param order the Order object to convert
     * @return the converted CreateOrderResponseDTO object
     */
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
                Messages.ORDER_CREATED
        );
    }

    /**
     * Converts an Order object to an OrderDTO object.
     *
     * @param order the Order object to convert
     * @return the converted OrderDTO object
     */
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

    /**
     * Converts a list of Order objects to a list of OrderDTO objects.
     *
     * @param orders the list of Order objects to convert
     * @return the converted list of OrderDTO objects
     */
    @Override
    public List<OrderDTO> toOrderDTOList(List<Order> orders) {
        if (orders == null) {
            return null;
        }

        return orders.stream()
                .map(this::toOrderDTO)
                .collect(Collectors.toList());
    }

    /**
     * Converts an Order object to a ProcessedOrderDTO object.
     *
     * @param order the Order object to convert
     * @return the converted ProcessedOrderDTO object
     */
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
