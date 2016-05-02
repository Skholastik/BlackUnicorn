package com.springapp.mvc.Entities;


import javax.persistence.*;

@Entity
@Table(name = "product_category_model_image")
public class ProductCategoryModelImage extends BaseModel {

    public ProductCategoryModelImage(){
        super();
    }

    @Column(name = "PATH")
    private String path;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "MODEL_ID", referencedColumnName = "ID")
    private ProductCategoryModel model;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ProductCategoryModel getModel() {
        return model;
    }

    public void setModel(ProductCategoryModel model) {
        this.model = model;
    }
}
