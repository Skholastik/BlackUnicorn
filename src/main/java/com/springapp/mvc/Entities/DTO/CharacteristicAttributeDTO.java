package com.springapp.mvc.Entities.DTO;


import org.hibernate.validator.constraints.NotEmpty;

public class CharacteristicAttributeDTO {
    private int id;

    @NotEmpty
    private String name;
    @NotEmpty
    private String unitOfMeasurement;
    @NotEmpty
    private String viewType;

    public CharacteristicAttributeDTO() {

    }

    public CharacteristicAttributeDTO(String name) {
        this.name = name;
    }

    public CharacteristicAttributeDTO(int id, String name, String unitOfMeasurement, String viewType) {
        this.id = id;
        this.name = name;
        this.unitOfMeasurement = unitOfMeasurement;
        this.viewType = viewType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    @Override
    public String toString() {
        return "Название характеристики: "+name+", Единица измерения: "+unitOfMeasurement;
    }
}
