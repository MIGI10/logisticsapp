package com.hackathon.inditex.service;

import com.hackathon.inditex.entity.LogisticsCenter;
import com.hackathon.inditex.repository.LogisticsCenterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogisticsCenterService {

    private final LogisticsCenterRepository repository;

    @Autowired
    public LogisticsCenterService(LogisticsCenterRepository repository) {
        this.repository = repository;
    }

    public void createCenter(LogisticsCenter center) {

        repository.findByCoordinates_LatitudeAndCoordinates_Longitude(
                center.getCoordinates().getLatitude(),
                center.getCoordinates().getLongitude()
        ).ifPresent(existing -> {
            throw new IllegalArgumentException("There is already a logistics center in that position.");
        });

        if (center.getCurrentLoad() > center.getMaxCapacity()) {
            throw new IllegalArgumentException("Current load cannot exceed max capacity.");
        }

        repository.save(center);
    }

    public List<LogisticsCenter> getAllCenters() {
        return repository.findAll();
    }

    public void updateCenter(Long id, LogisticsCenter updatedCenter) {

        LogisticsCenter existingCenter = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Center not found."));

        BeanUtils.copyProperties(updatedCenter, existingCenter, getNullPropertyNames(updatedCenter));

        if (existingCenter.getCurrentLoad() > existingCenter.getMaxCapacity()) {
            throw new IllegalArgumentException("Current load cannot exceed max capacity.");
        }

        repository.save(existingCenter);
    }

    public void deleteCenter(Long id) {
        repository.deleteById(id);
    }

    private String[] getNullPropertyNames(Object source) {

        List<String> nullPropertyNames = new ArrayList<>();

        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(source.getClass());

        for (PropertyDescriptor pd : pds) {
            try {
                if (pd.getReadMethod() != null) {
                    Object propertyValue = pd.getReadMethod().invoke(source);
                    if (propertyValue == null) {
                        nullPropertyNames.add(pd.getName());
                    }
                }
            } catch (Exception ignored) {}
        }

        return nullPropertyNames.toArray(new String[0]);
    }
}