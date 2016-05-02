package com.springapp.mvc.Entities.DTO;

public class CharacteristicDTO {
    private CharacteristicAttributeDTO attribute;
    private String value;

    public CharacteristicDTO() {
    }

    public CharacteristicDTO(String value) {
        this.value = value;
    }

    public CharacteristicAttributeDTO getAttribute() {
        return attribute;
    }

    public void setAttribute(CharacteristicAttributeDTO attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
