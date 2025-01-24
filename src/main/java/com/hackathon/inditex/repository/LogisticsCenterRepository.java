package com.hackathon.inditex.repository;

import com.hackathon.inditex.entity.LogisticsCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LogisticsCenterRepository extends JpaRepository<LogisticsCenter, Long> {
    Optional<LogisticsCenter> findByCoordinates_LatitudeAndCoordinates_Longitude(double latitude, double longitude);

    @Query("SELECT c FROM LogisticsCenter c WHERE LOWER(c.capacity) LIKE LOWER(CONCAT('%', :size, '%'))")
    List<LogisticsCenter> findSupportingCenters(@Param("size") String size);

    @Query("SELECT c FROM LogisticsCenter c WHERE LOWER(c.capacity) LIKE LOWER(CONCAT('%', :size, '%')) AND c.currentLoad < c.maxCapacity")
    List<LogisticsCenter> findAvailableCenters(@Param("size") String size);
}
