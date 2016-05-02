package com.springapp.mvc.Entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "unit_of_measurement")
public class UnitOfMeasurement extends BaseModel {

    public UnitOfMeasurement() {
        super();
    }

    public UnitOfMeasurement(String name) {
        super();
        this.name = name;
    }

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "unitOfMeasurement", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CharacteristicAttribute> attributeList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CharacteristicAttribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<CharacteristicAttribute> attributeList) {
        this.attributeList = attributeList;
    }
}
