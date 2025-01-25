package com.hackathon.inditex.repository;

import com.hackathon.inditex.Entities.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Center entity, using JPQL queries.
 */
@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {

    /**
     * Find a center by exact latitude and longitude coordinates.
     */
    @Query("SELECT c FROM Center c " +
            "WHERE c.coordinates.latitude = :latitude " +
            "  AND c.coordinates.longitude = :longitude")
    Optional<Center> findByCoordinates(@Param("latitude") double latitude,
                                       @Param("longitude") double longitude);

    /**
     * Find centers that support a given size (case-insensitive partial match on 'capacity').
     */
    @Query("SELECT c FROM Center c " +
            "WHERE LOWER(c.capacity) LIKE LOWER(CONCAT('%', :size, '%'))")
    List<Center> findSupportingCenters(@Param("size") String size);

    /**
     * Find centers that support a given size and have capacity available
     * (i.e., currentLoad < maxCapacity).
     */
    @Query("SELECT c FROM Center c " +
            "WHERE LOWER(c.capacity) LIKE LOWER(CONCAT('%', :size, '%')) " +
            "  AND c.currentLoad < c.maxCapacity")
    List<Center> findAvailableCenters(@Param("size") String size);
}
