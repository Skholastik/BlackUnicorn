package com.springapp.mvc.DAO.Interfaces;


import com.springapp.mvc.Entities.*;

import java.util.List;

public interface CategoryDao {
    Category getCategoryById(int categoryId);

    List<Category> getAllCategories();

    List<Category> getCategoryListByParentId(int parentId);

    List<Category> getCategoryByIdAndName(int parentId, String categoryName);

    void addCategoryList(List<Category> newCategoryList, int parentId);

    boolean checkCategoryExists(int parentId, String categoryName);

}
