package com.springapp.mvc.Controllers.AdminAPI;

import com.springapp.mvc.Entities.DTO.*;
import com.springapp.mvc.Service.Interfaces.ProductCategoryModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/productCategoryModel")
public class ProductCategoryModelAPI {

    @Autowired
    private ProductCategoryModelService productCategoryModelService;

    @RequestMapping(value = "/getProductCategoryModelList", method = RequestMethod.GET, produces = "application/json")
    public ProductCategoryModelListDTO getProductCategoryModels(@RequestParam int productCategoryId,
                                                                @RequestParam int maxResult,
                                                                @RequestParam int currentPage,
                                                                @RequestParam boolean needToReceiveAttributes) {
        return productCategoryModelService.getProductCategoryModel(productCategoryId, maxResult, currentPage, needToReceiveAttributes);
    }
}
