package com.springapp.mvc.DAO.Implementation;

import com.springapp.mvc.DAO.Interfaces.CategoryDao;
import com.springapp.mvc.Entities.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


@Repository("categoryDaoImpl")
public class CategoryDaoImpl implements CategoryDao {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Category getCategoryById(int categoryId) {
        return entityManager.find(Category.class, categoryId);
    }

    @Override
    public List<Category> getCategoryByIdAndName(int parentId, String categoryName) {
        if (parentId == 0) {
            return entityManager.createQuery("FROM Category c Where c.parentCategory.id=null AND c.name=:categoryName", Category.class)
                    .setParameter("categoryName", categoryName).getResultList();
        } else
            return entityManager.createQuery("FROM Category c Where c.parentCategory.id=:parentId AND c.name=:categoryName", Category.class)
                    .setParameter("parentId", parentId)
                    .setParameter("categoryName", categoryName).getResultList();
    }

    @Override
    public List<Category> getAllCategories() {
        return entityManager.createQuery("FROM Category", Category.class).getResultList();
    }


    @Override
    public List<Category> getCategoryListByParentId(int parentId) {
        if (parentId == 0)
            return entityManager.createQuery("FROM Category c Where c.parentCategory.id=null", Category.class).getResultList();
        else
            return entityManager.createQuery("FROM Category c Where c.parentCategory.id=:parentId", Category.class)
                    .setParameter("parentId", parentId).getResultList();
    }

    public void addCategoryList(List<Category> newCategoryList, int parentId) {

        Category parentCategory = parentId == 0 ? null : entityManager.find(Category.class, parentId);

        for (Category category : newCategoryList) {
            entityManager.persist(category);
            entityManager.flush();

            if (parentCategory != null) {
                category.setParentCategory(parentCategory);
                category.setPath(parentCategory.getPath() + "." + category.getId());

            } else
                category.setPath(String.valueOf(category.getId()));
        }

    }

    @Override
    public boolean checkCategoryExists(int parentId, String categoryName) {
        return getCategoryByIdAndName(parentId, categoryName).size() == 1;
    }

}
