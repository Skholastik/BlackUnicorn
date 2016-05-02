package com.springapp.mvc.Service.Interfaces;

import com.springapp.mvc.Entities.DTO.ProductCategoryModelDTO;
import com.springapp.mvc.Entities.DTO.ProductCategoryModelListDTO;
import com.springapp.mvc.Service.AncillaryServices.ResponseMessage;

public interface ProductCategoryModelService {
    ProductCategoryModelListDTO getProductCategoryModel(int categoryId, int maxResults, int currentPage, boolean needToReceiveAttributes);
    ResponseMessage addProductCategoryModel(int categoryId,ProductCategoryModelDTO productCategoryModelDTO);
}
