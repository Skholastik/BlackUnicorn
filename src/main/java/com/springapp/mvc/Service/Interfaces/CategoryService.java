package com.springapp.mvc.Service.Interfaces;

import com.springapp.mvc.Entities.DTO.CategoryDTO;
import com.springapp.mvc.Entities.DTO.CategoryListDTO;
import com.springapp.mvc.Entities.DTO.CharacteristicAttributeDTOList;
import com.springapp.mvc.Entities.DTO.PathDTO;
import com.springapp.mvc.Service.AncillaryServices.ResponseMessage;

import java.util.List;

public interface CategoryService {
    List <CategoryDTO> getCategoryListByParentId(int parentId);
    List <CategoryDTO> getAllCategories();
    List <CategoryDTO>  getCategoryListForCreationCategory();
    List<CategoryDTO> getCategoryListForCreationModelCategoryProduct();
    ResponseMessage checkCategoryExistence(int parentId,String categoryName);
    ResponseMessage checkCategoryAttributeExistence(int parentCategoryId, String attributeName, String unitOfMeasurement);
    ResponseMessage addCategoryList(CategoryListDTO newCategoryList, int parentId);
    ResponseMessage addCategoryAttribute(CharacteristicAttributeDTOList attributeList, int parentId);
    List <PathDTO> getFullPath(int categoryId);
    ResponseMessage changeCategoryName(String newName,int categoryId);
    ResponseMessage deleteCategory(int categoryId);

}
