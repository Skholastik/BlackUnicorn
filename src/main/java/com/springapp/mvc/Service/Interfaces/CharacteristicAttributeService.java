package com.springapp.mvc.Service.Interfaces;

import com.springapp.mvc.Entities.DTO.CharacteristicAttributeDTO;

import java.util.List;

public interface CharacteristicAttributeService {
    List<CharacteristicAttributeDTO> getAttributeList();
}
