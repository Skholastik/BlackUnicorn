package com.springapp.mvc.Service.Implementation;

import com.springapp.mvc.DAO.Interfaces.CategoryDao;
import com.springapp.mvc.DAO.Interfaces.CharacteristicAttributeDao;
import com.springapp.mvc.DAO.Interfaces.UnitOfMeasurementDao;
import com.springapp.mvc.DAO.Interfaces.ViewDao;
import com.springapp.mvc.Entities.*;
import com.springapp.mvc.Entities.DTO.*;
import com.springapp.mvc.Service.AncillaryServices.ResponseMessage;
import com.springapp.mvc.Service.Interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    UnitOfMeasurementDao unitOfMeasurementDao;

    @Autowired
    ViewDao viewDao;

    @Autowired
    CharacteristicAttributeDao attributeDao;

    @Override
    public List<CategoryDTO> getCategoryListByParentId(int parentId) {
        List<Category> categoryList = categoryDao.getCategoryListByParentId(parentId);
        return convertCategoryListToDTO(categoryList);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categoryList = categoryDao.getAllCategories();
        return convertCategoryListToDTO(categoryList);
    }

    @Override
    public List<CategoryDTO> getCategoryListForCreationCategory() {
        List<Category> categoryList = categoryDao.getAllCategories();
        List<Category> formattedCategoryList = new ArrayList<>();

        for (Category category : categoryList) {

            if (category.getModels().size() == 0) {
                formattedCategoryList.add(category);
            }
        }

        return convertCategoryListToDTO(formattedCategoryList);
    }

    @Override
    public List<CategoryDTO> getCategoryListForCreationModelCategoryProduct() {
        List<Category> categoryList = categoryDao.getAllCategories();
        List<Category> formattedCategoryList = new ArrayList<>();

        for (Category category : categoryList) {

            if (category.getCategoryList().size() == 0) {
                formattedCategoryList.add(category);
            }
        }
        return convertCategoryListToDTO(formattedCategoryList);
    }

    @Override
    public ResponseMessage checkCategoryExistence(int parentId, String categoryName) {

        if (categoryDao.checkCategoryExists(parentId, categoryName))
            return new ResponseMessage(true, "Категория свободна!");
        else
            return new ResponseMessage(false, "Категория с именем" + " '" + categoryName + "' " + "существует!");
    }

    @Override
    public ResponseMessage checkCategoryAttributeExistence(int parentCategoryId, String attributeName, String unitOfMeasurement) {
        Category category = categoryDao.getCategoryById(parentCategoryId);
        List<CharacteristicAttribute> attributeList = category.getCharacteristicAttributeList();
        attributeList.size();
        for (CharacteristicAttribute attribute : attributeList) {
            if (attribute.getName().equals(attributeName) &&
                    attribute.getUnitOfMeasurement().getName().equals(unitOfMeasurement))
                return new ResponseMessage(false, "Данный атрибут имеется у категории " + "'" + category.getName() + "'");
        }
        return new ResponseMessage(true, " ");
    }

    @Override
    public ResponseMessage addCategoryList(CategoryListDTO newCategoryListDTO, int parentId) {

        List<Category> categoryList = new ArrayList<>();
        List<Category> dbCategoryList = categoryDao.getCategoryListByParentId(parentId);
        checkAndCorrectCategoryList(dbCategoryList, newCategoryListDTO.getCategoryListDTO());

        try {
            for (CategoryDTO categoryDTO : newCategoryListDTO.getCategoryListDTO()) {
                Category category = new Category();
                category.setId(categoryDTO.getId());
                category.setName(categoryDTO.getName());
                categoryList.add(category);
            }
        } catch (NullPointerException e) {
            return new ResponseMessage(false, "Необходимо заполнить параметры!");
        }
        categoryDao.addCategoryList(categoryList, parentId);

        return new ResponseMessage(true, "Категория добавлена!");
    }

    public List<CategoryDTO> convertCategoryListToDTO(List<Category> categoryList) {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setName(category.getName());

            if (category.getModels().size() == 0)
                categoryDTO.setHasProductCategoryModel(false);
            else
                categoryDTO.setHasProductCategoryModel(true);

            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }

    public List<CategoryDTO> checkAndCorrectCategoryList(List<Category> dbCategoryList, List<CategoryDTO> newCategoryList) {

        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        for (int i = 0; i < dbCategoryList.size(); i++) {
            for (int z = 0; z < newCategoryList.size(); z++) {
                if (newCategoryList.get(z).getName().equals(dbCategoryList.get(i).getName()))
                    newCategoryList.remove(newCategoryList.get(z));
            }
        }
        return newCategoryList;
    }

    @Override
    public ResponseMessage addCategoryAttribute(CharacteristicAttributeDTOList newAttributeList, int categoryId) {
        try {
            List<CharacteristicAttribute> attributeList = attributeDao.getAttributeList();
            List<CharacteristicAttribute> categoryAttributeList = categoryDao.getCategoryById(categoryId).getCharacteristicAttributeList();

            if (attributeList.size() != 0)

                newAttributeList.setAttributeList(checkAndCorrectCharacteristicAttributeList(attributeList,
                        categoryAttributeList, newAttributeList.getAttributeList()));

            if (newAttributeList.getAttributeList().size() == 0)
                return new ResponseMessage(true, "Данные аттрибуты существуют у данной категории!");

            for (CharacteristicAttributeDTO attributeDTO : newAttributeList.getAttributeList()) {
                CharacteristicAttribute attribute;

                if (attributeDTO.getId() != 0)
                    attribute = attributeDao.getAttribute(attributeDTO.getId());
                else
                    attribute = createCharacteristicAttribute(attributeDTO);

                attributeList.add(attribute);
            }
        } catch (NullPointerException e) {
            return new ResponseMessage(false, "Необходимо заполнить все поля! " + e.getCause());
        }

        return new ResponseMessage(true, "Аттрибуты добавлены!");
    }


    /**
     * Проверяет массив на наличие дубликатов в БД. Необходима полная выборка аттрибутов из БД, выборка аттрибутов из
     * родительской категории(однако, можно бы было у самих аттрибутов взять список их категории, но с ростом БД этот метод
     * будет негативно влиять на производительность) и список новых аттрибутов. Далее проводится сравнение имени и единиц имерения
     * аттрибутов из БД и тех, которые желаем добавить. Если совпадения есть, то далее проходит проверка на содержание их в родительской
     * категории, если в родительской категории они имеются, то тогда элемент удаляется из массива новых элементов,если же нет-
     * ему присваивается ID аттрибута, в котором нашли совпадение.
     */
    public List<CharacteristicAttributeDTO> checkAndCorrectCharacteristicAttributeList(List<CharacteristicAttribute> dbAttributeList,
                                                                                       List<CharacteristicAttribute> categoryAttributeList,
                                                                                       List<CharacteristicAttributeDTO> newAttributeList) {
        for (int i = 0; i < dbAttributeList.size(); i++) {
            for (int z = 0; z < newAttributeList.size(); z++) {
                if (newAttributeList.get(z).getName().equals(dbAttributeList.get(i).getName()) &&
                        newAttributeList.get(z).getUnitOfMeasurement().equals(dbAttributeList.get(i)
                                .getUnitOfMeasurement().getName())) {
                    for (CharacteristicAttribute attribute : categoryAttributeList) {
                        if (attribute.getId() == dbAttributeList.get(i).getId())
                            newAttributeList.remove(newAttributeList.get(z));
                        else
                            newAttributeList.get(z).setId(dbAttributeList.get(i).getId());
                    }
                }
            }
        }
        return newAttributeList;

    }

    public CharacteristicAttribute createCharacteristicAttribute(CharacteristicAttributeDTO attributeDTO) {
        CharacteristicAttribute attribute = new CharacteristicAttribute();
        attribute.setName(attributeDTO.getName());
        attributeDao.addAttribute(attribute);

        if (unitOfMeasurementDao.checkUnitOfMeasurementExists(attributeDTO.getUnitOfMeasurement()))
            attribute.setUnitOfMeasurement(unitOfMeasurementDao.getUnitOfMeasurement(attributeDTO.getUnitOfMeasurement()));
        else
            attribute.setUnitOfMeasurement(createUnitOfMeasurement(attributeDTO.getUnitOfMeasurement()));


        attribute.setView(viewDao.getView(attributeDTO.getViewType()));
        return attribute;
    }

    public UnitOfMeasurement createUnitOfMeasurement(String name) {
        UnitOfMeasurement unitOfMeasurement = new UnitOfMeasurement();
        unitOfMeasurement.setName(name);
        unitOfMeasurementDao.addUnitOfMeasurement(unitOfMeasurement);
        return unitOfMeasurement;
    }

    @Override
    public List<PathDTO> getFullPath(int categoryId) {
        List<PathDTO> fullPath = new ArrayList<>();
        Category category = categoryDao.getCategoryById(categoryId);
        fullPath.add(new PathDTO(category.getId(), category.getName(), "Category"));

        while (category.getParentCategory() != null) {
            category = category.getParentCategory();
            fullPath.add(new PathDTO(category.getId(), category.getName(), "Category"));
        }

        Collections.reverse(fullPath);
        return fullPath;
    }

    @Override
    public ResponseMessage changeCategoryName(String newName, int categoryId) {
        try {
            Category category = categoryDao.getCategoryById(categoryId);
            category.setName(newName);
            return new ResponseMessage(true, "Имя успешно изменено!");
        } catch (NullPointerException e) {
            return new ResponseMessage(false, "Необходимо заполнить параметры!");
        }
    }

    @Override
    public ResponseMessage deleteCategory(int categoryId) {
        return null;
    }
}
