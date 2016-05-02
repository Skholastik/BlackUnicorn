package com.springapp.mvc.Entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "characteristic_value")
public class CharacteristicValue extends BaseModel {

    public CharacteristicValue(){
        super();
    }

    @Column(name = "VALUE")
    private String value;

    @OneToMany(mappedBy = "value", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductCategoryModelCharacteristics> characteristics;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ProductCategoryModelCharacteristics> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<ProductCategoryModelCharacteristics> characteristics) {
        this.characteristics = characteristics;
    }
}
