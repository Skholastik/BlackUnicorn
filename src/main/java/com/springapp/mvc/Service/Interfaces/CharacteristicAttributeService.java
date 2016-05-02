package com.springapp.mvc.Service.Interfaces;

import com.springapp.mvc.Entities.DTO.CharacteristicAttributeDTO;
import com.springapp.mvc.Service.AncillaryServices.ResponseMessage;

import java.util.List;

public interface CharacteristicAttributeService {
    List<CharacteristicAttributeDTO> getAttributeList();
}
