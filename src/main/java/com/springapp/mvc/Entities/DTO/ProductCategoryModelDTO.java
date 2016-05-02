package com.springapp.mvc.Entities.DTO;


import com.springapp.mvc.Entities.ProductCategoryModel;
import com.springapp.mvc.Entities.ProductCategoryModelCharacteristics;
import com.springapp.mvc.Entities.ProductCategoryModelImage;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryModelDTO {

    int id;
    String description;
    List<CharacteristicDTO> characteristicList = new ArrayList<>();
    List<String> imagePathList = new ArrayList<>();

    public ProductCategoryModelDTO() {
    }

    public ProductCategoryModelDTO(ProductCategoryModel productCategoryModel) {
        this.description = productCategoryModel.getDescription().getText();
        this.id = productCategoryModel.getId();

        for (ProductCategoryModelCharacteristics modelCharacteristics : productCategoryModel.getCharacteristics()) {
            characteristicList.add(new CharacteristicDTO((modelCharacteristics.getValue().getValue())));
        }

        for (ProductCategoryModelImage modelImages : productCategoryModel.getImages()) {
            imagePathList.add(modelImages.getPath());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CharacteristicDTO> getCharacteristicList() {
        return characteristicList;
    }

    public void setCharacteristicList(List<CharacteristicDTO> characteristicList) {
        this.characteristicList = characteristicList;
    }

    public List<String> getImagePathList() {
        return imagePathList;
    }

    public void setImagePathList(List<String> imagePathList) {
        this.imagePathList = imagePathList;
    }
}
