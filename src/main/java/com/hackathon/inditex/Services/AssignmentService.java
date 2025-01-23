package com.hackathon.inditex.Services;

import com.hackathon.inditex.DTO.ProcessedOrderDTO;
import com.hackathon.inditex.Entities.LogisticsCenter;
import com.hackathon.inditex.Entities.Order;
import com.hackathon.inditex.Repositories.LogisticsCenterRepository;
import com.hackathon.inditex.Repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AssignmentService {

    private final OrderRepository orderRepository;

    private final LogisticsCenterRepository centerRepository;

    @Autowired
    public AssignmentService(OrderRepository orderRepository, LogisticsCenterRepository centerRepository) {
        this.orderRepository = orderRepository;
        this.centerRepository = centerRepository;
    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {

        final int R = 6371; // Radius of the Earth in km

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // in km
    }

    @Transactional
    public Map<String, List<ProcessedOrderDTO>> assignPendingOrders() {

        List<Order> pendingOrders = orderRepository.findByStatusIgnoreCaseOrderByIdAsc("PENDING");

        List<ProcessedOrderDTO> results = new ArrayList<>();

        for (Order order : pendingOrders) {
            ProcessedOrderDTO result = new ProcessedOrderDTO();
            result.setOrderId(order.getId());
            result.setStatus(order.getStatus());

            List<LogisticsCenter> supportingCenters = centerRepository.findSupportingCenters(order.getSize());

            if (supportingCenters.isEmpty()) {
                // No centers support the order's size
                result.setDistance(null);
                result.setAssignedLogisticsCenter(null);
                result.setMessage("No available centers support the order type.");
                results.add(result);
                continue;
            }

            List<LogisticsCenter> availableCenters = centerRepository.findAvailableCenters(order.getSize());

            if (availableCenters.isEmpty()) {
                // Centers support the size but are at max capacity
                result.setDistance(null);
                result.setAssignedLogisticsCenter(null);
                result.setMessage("All centers are at maximum capacity.");
                results.add(result);
                continue;
            }


            LogisticsCenter closestCenter = null;
            double minDistance = Double.MAX_VALUE;

            for (LogisticsCenter center : availableCenters) {

                double distance = haversine(
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
                result.setMessage("No available centers.");
            }

            results.add(result);
        }

        Map<String, List<ProcessedOrderDTO>> response = new HashMap<>();
        response.put("processed-orders", results);
        return response;
    }
}
