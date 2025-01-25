package com.hackathon.inditex.repository;

import com.hackathon.inditex.entity.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Center entity
 */
@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {
    Optional<Center> findByCoordinates_LatitudeAndCoordinates_Longitude(double latitude, double longitude);

    @Query("SELECT center FROM Center center WHERE LOWER(center.capacity) LIKE LOWER(CONCAT('%', :size, '%'))")
    List<Center> findSupportingCenters(@Param("size") String size);

    @Query("SELECT center FROM Center center WHERE LOWER(center.capacity) LIKE LOWER(CONCAT('%', :size, '%')) AND center.currentLoad < center.maxCapacity")
    List<Center> findAvailableCenters(@Param("size") String size);
}
