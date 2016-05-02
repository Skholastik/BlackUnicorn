package com.springapp.mvc.Entities;


import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "ProductCategoryModel.getNumberOfAllRecords",
                query = "SELECT P.id FROM ProductCategoryModel P WHERE P.category.id=:categoryId")
})
@Table(name = "product_category_model")
public class ProductCategoryModel extends BaseModel {

    @Column(name = "MODERATED")
    private int moderated;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "DESCRIPTION_ID", referencedColumnName = "ID")
    private ProductCategoryModelDescription description;

    @OneToMany(mappedBy = "model", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductCategoryModelCharacteristics> characteristics;

    @OneToMany(mappedBy = "model", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductCategoryModelImage> images;

    public ProductCategoryModel() {
        super();
    }

    public int getModerated() {
        return moderated;
    }

    public void setModerated(int moderated) {
        this.moderated = moderated;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ProductCategoryModelDescription getDescription() {
        return description;
    }

    public void setDescription(ProductCategoryModelDescription description) {
        this.description = description;
    }

    public List<ProductCategoryModelCharacteristics> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<ProductCategoryModelCharacteristics> characteristics) {
        this.characteristics = characteristics;
    }

    public List<ProductCategoryModelImage> getImages() {
        return images;
    }

    public void setImages(List<ProductCategoryModelImage> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Model: " + " Moderated: " + moderated + "," + " Characteristics: " + getCharacteristics();
    }
}
