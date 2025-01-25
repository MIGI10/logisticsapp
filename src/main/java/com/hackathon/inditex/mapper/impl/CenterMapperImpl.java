package com.hackathon.inditex.mapper.impl;

import com.hackathon.inditex.dto.CenterDTO;
import com.hackathon.inditex.dto.CoordinatesDTO;
import com.hackathon.inditex.entity.Center;
import com.hackathon.inditex.entity.Coordinates;
import com.hackathon.inditex.mapper.CenterMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CenterMapperImpl implements CenterMapper {

    @Override
    public Center toCenter(CenterDTO dto) {

        if (dto == null) {
            return null;
        }

        Center center = new Center();
        center.setId(dto.getId());
        center.setName(dto.getName());
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

    @Override
    public CenterDTO toCenterDTO(Center center) {

        if (center == null) {
            return null;
        }

        CenterDTO dto = new CenterDTO();
        dto.setId(center.getId());
        dto.setName(center.getName());
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