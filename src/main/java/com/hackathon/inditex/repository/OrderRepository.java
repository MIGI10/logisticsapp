package com.hackathon.inditex.repository;

import com.hackathon.inditex.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Order entity, using JPQL queries.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Find orders by status (case-insensitive), sorted by ID ascending.
     */
    @Query("SELECT o FROM Order o " +
            "WHERE LOWER(o.status) = LOWER(:status) " +
            "ORDER BY o.id ASC")
    List<Order> findByStatus(@Param("status") String status);
}
