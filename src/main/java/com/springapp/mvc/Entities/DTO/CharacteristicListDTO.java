package com.springapp.mvc.Entities.DTO;

import java.util.List;

public class CharacteristicListDTO {
    List<CharacteristicDTO> characteristicList;

    public List<CharacteristicDTO> getCharacteristicList() {
        return characteristicList;
    }

    public void setCharacteristicList(List<CharacteristicDTO> characteristicList) {
        this.characteristicList = characteristicList;
    }
}
