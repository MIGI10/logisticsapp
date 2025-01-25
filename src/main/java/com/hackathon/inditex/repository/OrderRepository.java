package com.hackathon.inditex.repository;

import com.hackathon.inditex.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Order entity
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatusIgnoreCaseOrderByIdAsc(String status);
}
