package com.hackathon.inditex.service.impl;

import com.hackathon.inditex.constant.Messages;
import com.hackathon.inditex.dto.CenterDTO;
import com.hackathon.inditex.entity.Center;
import com.hackathon.inditex.exception.CenterAlreadyExistsException;
import com.hackathon.inditex.exception.CenterExceedsCapacityException;
import com.hackathon.inditex.exception.CenterNotFoundException;
import com.hackathon.inditex.mapper.CenterMapper;
import com.hackathon.inditex.repository.CenterRepository;
import com.hackathon.inditex.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for the Center entity.
 */
@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {

    private final CenterRepository repository;
    private final CenterMapper centerMapper;

    /**
     * Creates a new center.
     * @param centerRequest The center to be created.
     */
    @Override
    public void createCenter(CenterDTO centerRequest) {

        Center center = centerMapper.toCenter(centerRequest);

        repository.findByCoordinates(
                center.getCoordinates().getLatitude(),
                center.getCoordinates().getLongitude()
        ).ifPresent(existing -> {
            throw new CenterAlreadyExistsException(Messages.CENTER_EXISTS_ERR);
        });

        if (center.getCurrentLoad() > center.getMaxCapacity()) {
            throw new CenterExceedsCapacityException(Messages.CENTER_EXCEEDS_CAPACITY_ERR);
        }

        repository.save(center);
    }

    /**
     * Retrieves all centers.
     * @return A list of all centers.
     */
    @Override
    public List<CenterDTO> getAllCenters() {
        List<Center> centers = repository.findAll();
        return centerMapper.toCenterDTOList(centers);
    }

    /** Updates a center.
     * @param id The id of the center to be updated.
     * @param updatedCenterRequest The updated center.
     */
    @Override
    public void updateCenter(Long id, CenterDTO updatedCenterRequest) {

        Center existingCenter = repository.findById(id)
                .orElseThrow(() -> new CenterNotFoundException(Messages.CENTER_NOT_FOUND_ERR));

        Center updatedCenter = centerMapper.toCenter(updatedCenterRequest);

        BeanUtils.copyProperties(updatedCenter, existingCenter, getNullPropertyNames(updatedCenter));

        if (existingCenter.getCurrentLoad() > existingCenter.getMaxCapacity()) {
            throw new CenterExceedsCapacityException(Messages.CENTER_EXCEEDS_CAPACITY_ERR);
        }

        repository.save(existingCenter);
    }

    /** Deletes a center.
     * @param id The id of the center to be deleted.
     */
    @Override
    public void deleteCenter(Long id) {
        repository.deleteById(id);
    }

    /**
     * Returns the names of the properties that are null in the source object.
     * @param source The source object.
     * @return The names of the properties that are null in the source object.
     */
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