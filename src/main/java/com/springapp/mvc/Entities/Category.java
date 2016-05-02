package com.springapp.mvc.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category extends BaseModel {

    @Column(name = "NAME")
    private String name;

    /* @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)*/
    @Column(name = "PATH")
    private String path;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "category_attributes",
            joinColumns = @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ATTRIBUTE_ID", referencedColumnName = "ID")
    )
    private List<CharacteristicAttribute> characteristicAttributeList;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID", nullable = true)
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Category> categoryList;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductCategoryModel> models;

    public Category() {
        super();
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, StringBuilder path) {
        super();
        this.name = name;
        this.path = path.toString();
    }

    public void addCategory(Category category) {
        category.setParentCategory(this);
        categoryList.add(category);
    }


    @Override
    public String toString() {
        return "ID: " + getId() + " Name: " + getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CharacteristicAttribute> getCharacteristicAttributeList() {
        return characteristicAttributeList;
    }

    public void setCharacteristicAttributeList(List<CharacteristicAttribute> characteristicAttributeList) {
        this.characteristicAttributeList = characteristicAttributeList;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<ProductCategoryModel> getModels() {
        return models;
    }

    public void setModels(List<ProductCategoryModel> models) {
        this.models = models;
    }


}
