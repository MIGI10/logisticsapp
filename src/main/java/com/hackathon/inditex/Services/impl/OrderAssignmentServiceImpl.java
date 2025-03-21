package com.hackathon.inditex.Services.impl;

import com.hackathon.inditex.Constants.Messages;
import com.hackathon.inditex.DTO.ProcessedOrderDTO;
import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.Entities.Order;
import com.hackathon.inditex.Mappers.OrderMapper;
import com.hackathon.inditex.Repositories.CenterRepository;
import com.hackathon.inditex.Repositories.OrderRepository;
import com.hackathon.inditex.Services.OrderAssignmentService;
import com.hackathon.inditex.Utils.GeoUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service class for order assignment.
 */
@Service
@RequiredArgsConstructor
public class OrderAssignmentServiceImpl implements OrderAssignmentService {

    public static final String TEST = "test";
    private final OrderRepository orderRepository;
    private final CenterRepository centerRepository;
    private final OrderMapper orderMapper;

    /**
     * Assigns pending orders to available logistics centers.
     * @return A map containing the processed orders.
     */
    @Override
    @Transactional
    public Map<String, List<ProcessedOrderDTO>> assignPendingOrders() {

        List<Order> pendingOrders = orderRepository.findByStatus("PENDING");
        List<ProcessedOrderDTO> results = new ArrayList<>();

        for (Order order : pendingOrders) {

            ProcessedOrderDTO result = orderMapper.toProcessedOrderDTO(order);

            List<Center> supportingCenters = centerRepository.findSupportingCenters(order.getSize());
            if (supportingCenters.isEmpty()) {
                // No centers support the order's size
                result.setDistance(null);
                result.setAssignedLogisticsCenter(null);
                result.setMessage(Messages.CENTER_UNSUPPORTED_ERR);
                results.add(result);
                continue;
            }

            List<Center> availableCenters = centerRepository.findAvailableCenters(order.getSize());
            if (availableCenters.isEmpty()) {
                // Centers support the size but are at max capacity
                result.setDistance(null);
                result.setAssignedLogisticsCenter(null);
                result.setMessage(Messages.CENTER_FULL_ERR);
                results.add(result);
                continue;
            }

            Center closestCenter = null;
            double minDistance = Double.MAX_VALUE;

            for (Center center : availableCenters) {
                double distance = GeoUtil.haversine(
                        order.getCoordinates().getLatitude(),
                        order.getCoordinates().getLongitude(),
                        center.getCoordinates().getLatitude(),
                        center.getCoordinates().getLongitude()
                );
                if (distance < minDistance) {
                    minDistance = distance;
                    closestCenter = center;
                }
            }

            if (closestCenter != null) {

                order.setAssignedCenter(closestCenter);
                order.setStatus("ASSIGNED");
                orderRepository.save(order);

                closestCenter.setCurrentLoad(closestCenter.getCurrentLoad() + 1);
                centerRepository.save(closestCenter);

                result.setDistance(minDistance);
                result.setAssignedLogisticsCenter(closestCenter.getName());
                result.setStatus(order.getStatus());
                result.setMessage(null);
            } else {
                result.setDistance(null);
                result.setAssignedLogisticsCenter(null);
                result.setMessage(Messages.CENTER_UNAVAILABLE_ERR);
            }

            results.add(result);
        }

        Map<String, List<ProcessedOrderDTO>> response = new HashMap<>();
        response.put("processed-orders", results);
        return response;
    }
}
