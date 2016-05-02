package com.springapp.mvc.Entities.DTO;


public class CategoryDTO {
    int id;
    String name;
    boolean hasProductCategoryModel;

    public CategoryDTO() {

    }

    public CategoryDTO(int id, String name, boolean hasProductCategoryModel) {
        this.id = id;
        this.name = name;
        this.hasProductCategoryModel = hasProductCategoryModel;
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

    public boolean isHasProductCategoryModel() {
        return hasProductCategoryModel;
    }

    public void setHasProductCategoryModel(boolean hasProductCategoryModel) {
        this.hasProductCategoryModel = hasProductCategoryModel;
    }
}
