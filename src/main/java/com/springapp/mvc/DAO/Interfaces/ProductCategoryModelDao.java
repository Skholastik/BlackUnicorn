package com.springapp.mvc.DAO.Interfaces;


import com.springapp.mvc.Entities.ProductCategoryModel;

import java.util.List;

public interface ProductCategoryModelDao {
    List<ProductCategoryModel> getProductCategoryModelListByProductCategoryId(int categoryId, int maxResults, int firstResult);
    int getNumberOfAllRecordsCurrentProductCategory(int productCategoryId);
}
