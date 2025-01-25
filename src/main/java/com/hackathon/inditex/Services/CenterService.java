package com.hackathon.inditex.Services;

import com.hackathon.inditex.DTO.CenterDTO;

import java.util.List;

/**
 * Service for Center entity
 */
public interface CenterService {
    void createCenter(CenterDTO center);

    List<CenterDTO> getAllCenters();

    void updateCenter(Long id, CenterDTO updatedCenter);

    void deleteCenter(Long id);
}
