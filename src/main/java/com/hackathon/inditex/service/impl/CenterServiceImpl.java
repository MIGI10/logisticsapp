package com.hackathon.inditex.service.impl;

import com.hackathon.inditex.dto.CenterDTO;
import com.hackathon.inditex.entity.Center;
import com.hackathon.inditex.exception.CenterAlreadyExistsException;
import com.hackathon.inditex.exception.CenterExceedsCapacityException;
import com.hackathon.inditex.exception.CenterNotFoundException;
import com.hackathon.inditex.mapper.CenterMapper;
import com.hackathon.inditex.repository.CenterRepository;
import com.hackathon.inditex.service.CenterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

@Service
public class CenterServiceImpl implements CenterService {

    private final CenterRepository repository;
    private final CenterMapper centerMapper;

    @Autowired
    public CenterServiceImpl(CenterRepository repository, CenterMapper centerMapper) {
        this.repository = repository;
        this.centerMapper = centerMapper;
    }

    @Override
    public void createCenter(CenterDTO centerRequest) {

        Center center = centerMapper.toCenter(centerRequest);

        repository.findByCoordinates_LatitudeAndCoordinates_Longitude(
                center.getCoordinates().getLatitude(),
                center.getCoordinates().getLongitude()
        ).ifPresent(existing -> {
            throw new CenterAlreadyExistsException("There is already a logistics center in that position.");
        });

        if (center.getCurrentLoad() > center.getMaxCapacity()) {
            throw new CenterExceedsCapacityException("Current load cannot exceed max capacity.");
        }

        repository.save(center);
    }

    @Override
    public List<CenterDTO> getAllCenters() {
        List<Center> centers = repository.findAll();
        return centerMapper.toCenterDTOList(centers);
    }

    @Override
    public void updateCenter(Long id, CenterDTO updatedCenterRequest) {

        Center existingCenter = repository.findById(id)
                .orElseThrow(() -> new CenterNotFoundException("Center not found."));

        Center updatedCenter = centerMapper.toCenter(updatedCenterRequest);

        BeanUtils.copyProperties(updatedCenter, existingCenter, getNullPropertyNames(updatedCenter));

        if (existingCenter.getCurrentLoad() > existingCenter.getMaxCapacity()) {
            throw new CenterExceedsCapacityException("Current load cannot exceed max capacity.");
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