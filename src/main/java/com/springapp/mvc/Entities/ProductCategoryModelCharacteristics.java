package com.springapp.mvc.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "product_category_model_characteristics")
public class ProductCategoryModelCharacteristics extends BaseModel {

    public ProductCategoryModelCharacteristics(){
        super();
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MODEL_ID", referencedColumnName = "ID")
    private ProductCategoryModel model;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "VALUE_ID", referencedColumnName = "ID")
    private CharacteristicValue value;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ATTRIBUTE_ID", referencedColumnName = "ID")
    private CharacteristicAttribute attribute;

    public ProductCategoryModel getModel() {
        return model;
    }

    public void setModel(ProductCategoryModel model) {
        this.model = model;
    }

    public CharacteristicValue getValue() {
        return value;
    }

    public void setValue(CharacteristicValue value) {
        this.value = value;
    }

    public CharacteristicAttribute getAttribute() {
        return attribute;
    }

    public void setAttribute(CharacteristicAttribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return attribute.getName() + " " + value;
    }
}
