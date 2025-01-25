package com.hackathon.inditex.mapper;

import com.hackathon.inditex.dto.CenterDTO;
import com.hackathon.inditex.entity.Center;

import java.util.List;

public interface CenterMapper {
    Center toCenter(CenterDTO dto);

    CenterDTO toCenterDTO(Center center);

    List<CenterDTO> toCenterDTOList(List<Center> orders);
}
