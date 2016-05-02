package com.springapp.mvc.Entities;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_category_model_description")
public class ProductCategoryModelDescription extends BaseModel {

    public ProductCategoryModelDescription(){
        super();
    }

    @Column(name = "TEXT")
    private String text;

    @OneToMany(mappedBy = "description", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductCategoryModel> productCategoryModels;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ProductCategoryModel> getProductCategoryModels() {
        return productCategoryModels;
    }

    public void setProductCategoryModels(List<ProductCategoryModel> productCategoryModels) {
        this.productCategoryModels = productCategoryModels;
    }
}
