package com.hackathon.inditex.mapper.impl;

import com.hackathon.inditex.dto.CenterDTO;
import com.hackathon.inditex.dto.CoordinatesDTO;
import com.hackathon.inditex.Entities.Center;
import com.hackathon.inditex.Entities.Coordinates;
import com.hackathon.inditex.mapper.CenterMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the CenterMapper interface.
 */
@Component
public class CenterMapperImpl implements CenterMapper {

    /**
     * Converts a CenterDTO object to a Center object.
     *
     * @param dto the CenterDTO object to convert
     * @return the converted Center object
     */
    @Override
    public Center toCenter(CenterDTO dto) {

        if (dto == null) {
            return null;
        }

        Center center = new Center();
        center.setId(dto.getId());
        center.setName(dto.getName());
        center.setCapacity(dto.getCapacity());
        center.setStatus(dto.getStatus());
        center.setMaxCapacity(dto.getMaxCapacity());
        center.setCurrentLoad(dto.getCurrentLoad());

        if (dto.getCoordinates() != null) {
            Coordinates coords = new Coordinates();
            coords.setLatitude(dto.getCoordinates().getLatitude());
            coords.setLongitude(dto.getCoordinates().getLongitude());
            center.setCoordinates(coords);
        }

        return center;
    }

    /**
     * Converts a Center object to a CenterDTO object.
     *
     * @param center the Center object to convert
     * @return the converted CenterDTO object
     */
    @Override
    public CenterDTO toCenterDTO(Center center) {

        if (center == null) {
            return null;
        }

        CenterDTO dto = new CenterDTO();
        dto.setId(center.getId());
        dto.setName(center.getName());
        dto.setCapacity(center.getCapacity());
        dto.setStatus(center.getStatus());
        dto.setMaxCapacity(center.getMaxCapacity());
        dto.setCurrentLoad(center.getCurrentLoad());

        if (center.getCoordinates() != null) {
            CoordinatesDTO coordsDTO = new CoordinatesDTO();
            coordsDTO.setLatitude(center.getCoordinates().getLatitude());
            coordsDTO.setLongitude(center.getCoordinates().getLongitude());
            dto.setCoordinates(coordsDTO);
        }

        return dto;
    }

    /**
     * Converts a list of Center objects to a list of CenterDTO objects.
     *
     * @param orders the list of Center objects to convert
     * @return the converted list of CenterDTO objects
     */
    @Override
    public List<CenterDTO> toCenterDTOList(List<Center> orders) {
        if (orders == null) {
            return null;
        }

        return orders.stream()
                .map(this::toCenterDTO)
                .collect(Collectors.toList());
    }
}