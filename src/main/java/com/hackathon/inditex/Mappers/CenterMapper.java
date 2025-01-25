package com.hackathon.inditex.Mappers;

import com.hackathon.inditex.DTO.CenterDTO;
import com.hackathon.inditex.Entities.Center;

import java.util.List;

/**
 * Mapper for Center entity
 */
public interface CenterMapper {

    Center toCenter(CenterDTO dto);

    CenterDTO toCenterDTO(Center center);

    List<CenterDTO> toCenterDTOList(List<Center> orders);
}
