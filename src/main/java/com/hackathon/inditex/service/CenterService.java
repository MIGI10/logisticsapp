package com.hackathon.inditex.service;

import com.hackathon.inditex.entity.Center;

import java.util.List;

public interface CenterService {
    void createCenter(Center center);

    List<Center> getAllCenters();

    void updateCenter(Long id, Center updatedCenter);

    void deleteCenter(Long id);
}
