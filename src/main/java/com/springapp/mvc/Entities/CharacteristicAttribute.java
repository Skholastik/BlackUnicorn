package com.springapp.mvc.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "characteristic_attribute")
public class CharacteristicAttribute extends BaseModel {

    public CharacteristicAttribute() {
        super();
    }

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "characteristicAttributeList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Category> categoryList;
    ;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "UNIT_OF_MEASUREMENT_ID", referencedColumnName = "ID")
    private UnitOfMeasurement unitOfMeasurement;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "VIEW_ID", referencedColumnName = "ID")
    private View view;

    @OneToMany(mappedBy = "attribute", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductCategoryModelCharacteristics> characteristics;

    /*
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
    @ManyToMany(mappedBy = "attributes",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductCategory> productCategories;*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public UnitOfMeasurement getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public List<ProductCategoryModelCharacteristics> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<ProductCategoryModelCharacteristics> characteristics) {
        this.characteristics = characteristics;
    }

    @Override
    public String toString() {
        return "Characteristic Attribute:" + " Name: " + name + "," + " Unit of measurement: " + unitOfMeasurement.getName();
    }
}
