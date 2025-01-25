package com.hackathon.inditex.service;

import com.hackathon.inditex.dto.CenterDTO;

import java.util.List;

public interface CenterService {
    void createCenter(CenterDTO center);

    List<CenterDTO> getAllCenters();

    void updateCenter(Long id, CenterDTO updatedCenter);

    void deleteCenter(Long id);
}
