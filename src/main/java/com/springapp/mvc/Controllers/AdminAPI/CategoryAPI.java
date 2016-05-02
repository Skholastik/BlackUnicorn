package com.springapp.mvc.Controllers.AdminAPI;

import com.springapp.mvc.Entities.DTO.*;
import com.springapp.mvc.Service.AncillaryServices.ResponseMessage;
import com.springapp.mvc.Service.Interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/category")
public class CategoryAPI {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/getCategoryListByParentId", method = RequestMethod.GET)
    public List<CategoryDTO> getCategoryListByParentId(@RequestParam int parentId) {
        return categoryService.getCategoryListByParentId(parentId);
    }

    @RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @RequestMapping(value = "/getCategoryListForCreationCategory", method = RequestMethod.GET)
    public List<CategoryDTO> getCategoryListForCreationCategory() {
        return categoryService.getCategoryListForCreationCategory();
    }

    @RequestMapping(value = "/getCategoryListForCreationModelCategoryProduct", method = RequestMethod.GET)
    public List<CategoryDTO> getCategoryListForCreationModelCategoryProduct() {
        return categoryService.getCategoryListForCreationModelCategoryProduct();
    }

    @RequestMapping(value = "/checkCategoryExistence", method = RequestMethod.GET)
    public ResponseMessage checkCategoryExistence(@RequestParam int parentId,
                                                  @RequestParam String categoryName) {
        return categoryService.checkCategoryExistence(parentId, categoryName);
    }

    @RequestMapping(value = "/checkCategoryAttributeExistence", method = RequestMethod.GET)
    public ResponseMessage checkAttributeExistence(@RequestParam String attributeName,
                                                   @RequestParam String unitOfMeasurement,
                                                   @RequestParam int parentCategoryId) {
        return categoryService.checkCategoryAttributeExistence(parentCategoryId, attributeName, unitOfMeasurement);
    }

    @RequestMapping(value = "/addCategoryList", method = RequestMethod.POST, produces = "application/json")
    public ResponseMessage addCategoryList(@RequestParam int parentId,
                                           @RequestBody CategoryListDTO categoryListDTO) {

        return categoryService.addCategoryList(categoryListDTO, parentId);
    }

    @RequestMapping(value = "/addCategoryAttributes", method = RequestMethod.POST, produces = "application/json")
    public ResponseMessage addCategoryAttributes(@RequestParam int categoryId,
                                                 @RequestBody CharacteristicAttributeDTOList characteristicAttributeList) {

        return categoryService.addCategoryAttribute(characteristicAttributeList, categoryId);
    }

    @RequestMapping(value = "/getFullPath", method = RequestMethod.GET, produces = "application/json")
    public List<PathDTO> getFullPath(@RequestParam int categoryId) {
        return categoryService.getFullPath(categoryId);
    }

    @RequestMapping(value = "/changeCategoryName", method = RequestMethod.GET, produces = "application/json")
    public ResponseMessage changeCategoryName(@RequestParam String newName,
                                              @RequestParam int categoryId)  {
        return categoryService.changeCategoryName(newName,categoryId);
    }

    @RequestMapping(value = "/deleteCategory", method = RequestMethod.GET, produces = "application/json")
    public ResponseMessage deleteCategory(@RequestParam int categoryId)  {
        return categoryService.deleteCategory(categoryId);
    }

}
