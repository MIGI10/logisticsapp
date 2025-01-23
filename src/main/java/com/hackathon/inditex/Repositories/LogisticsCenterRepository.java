package com.hackathon.inditex.Repositories;

import com.hackathon.inditex.Entities.LogisticsCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogisticsCenterRepository extends JpaRepository<LogisticsCenter, Long> {
    Optional<LogisticsCenter> findByCoordinates_LatitudeAndCoordinates_Longitude(double latitude, double longitude);
}
