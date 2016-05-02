package com.springapp.mvc.Entities.DTO;

import com.springapp.mvc.Entities.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryListDTO {
    private List<CategoryDTO> categoryListDTO=new ArrayList<>();

    public void addCategoryDTO(CategoryDTO categoryDTO){
        categoryListDTO.add(categoryDTO);
    }

    public List<CategoryDTO> getCategoryListDTO() {
        return categoryListDTO;
    }

    public void setCategoryList(List<CategoryDTO> categoryListDTO) {
        this.categoryListDTO = categoryListDTO;
    }
}
