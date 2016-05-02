package com.springapp.mvc.Service.Implementation;

import com.springapp.mvc.DAO.Interfaces.CharacteristicAttributeDao;
import com.springapp.mvc.Entities.CharacteristicAttribute;
import com.springapp.mvc.Entities.DTO.CharacteristicAttributeDTO;
import com.springapp.mvc.Service.AncillaryServices.ResponseMessage;
import com.springapp.mvc.Service.Interfaces.CharacteristicAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CharacteristicAttributeServiceImpl implements CharacteristicAttributeService {

    @Autowired
    CharacteristicAttributeDao attributeDao;

    @Override
    public List<CharacteristicAttributeDTO> getAttributeList() {

        List<CharacteristicAttributeDTO> attributeDTOList = new ArrayList<>();

        for (CharacteristicAttribute attribute : attributeDao.getAttributeList()) {
            attributeDTOList.add(
                    new CharacteristicAttributeDTO(attribute.getId(),
                            attribute.getName(),
                            attribute.getUnitOfMeasurement().getName(),
                            attribute.getView().getType()));
        }

        return attributeDTOList;
    }
}
