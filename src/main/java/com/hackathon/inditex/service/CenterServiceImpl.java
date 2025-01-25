package com.hackathon.inditex.service;

import com.hackathon.inditex.entity.Center;
import com.hackathon.inditex.repository.CenterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

@Service
public class CenterServiceImpl implements CenterService {

    private final CenterRepository repository;

    @Autowired
    public CenterServiceImpl(CenterRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createCenter(Center center) {

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

    @Override
    public List<Center> getAllCenters() {
        return repository.findAll();
    }

    @Override
    public void updateCenter(Long id, Center updatedCenter) {

        Center existingCenter = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Center not found."));

        BeanUtils.copyProperties(updatedCenter, existingCenter, getNullPropertyNames(updatedCenter));

        if (existingCenter.getCurrentLoad() > existingCenter.getMaxCapacity()) {
            throw new IllegalArgumentException("Current load cannot exceed max capacity.");
        }

        repository.save(existingCenter);
    }

    @Override
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