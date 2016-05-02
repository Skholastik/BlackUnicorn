package com.springapp.mvc.Controllers.AdminAPI;

import com.springapp.mvc.Entities.DTO.CharacteristicAttributeDTO;
import com.springapp.mvc.Service.Interfaces.CharacteristicAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/characteristicAttribute")
public class CharacteristicAttributeAPI {

    @Autowired
    private CharacteristicAttributeService viewService;

    @RequestMapping(value = "/getCharacteristicAttributeList", method = RequestMethod.GET)
    public List<CharacteristicAttributeDTO> getCharacteristicAttributeList() {
        return viewService.getAttributeList();
    }
}
