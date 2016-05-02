package com.springapp.mvc.Entities.DTO;


import java.util.ArrayList;
import java.util.List;

public class ProductCategoryModelListDTO {

    int numberOfPages;
    List<CharacteristicAttributeDTO> attributeList = new ArrayList<>();
    List<ProductCategoryModelDTO> modelList = new ArrayList<>();


    public void addProductCategoryModelDTO(ProductCategoryModelDTO productCategoryModelDTO) {
        this.modelList.add(productCategoryModelDTO);
    }

    public void addCharacteristicAttribute(CharacteristicAttributeDTO attributes) {
        this.attributeList.add(attributes);
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<CharacteristicAttributeDTO> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<CharacteristicAttributeDTO> attributeList) {
        this.attributeList = attributeList;
    }

    public List<ProductCategoryModelDTO> getModelList() {
        return modelList;
    }

    public void setModelList(List<ProductCategoryModelDTO> modelList) {
        this.modelList = modelList;
    }
}
