package com.springapp.mvc.DAO.Interfaces;

import com.springapp.mvc.Entities.CharacteristicAttribute;

import java.util.List;

public interface CharacteristicAttributeDao {
    List<CharacteristicAttribute> getAttributeList();
    CharacteristicAttribute getAttribute(int id);
    CharacteristicAttribute addAttribute(CharacteristicAttribute attribute);
}
