package org.example.service;

import org.example.dao.CategoryDao;
import org.example.model.Category;

import java.util.List;

public class CategoryService {
    private final CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public void createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        categoryDao.save(category);
    }

    public Category findById(Long id) {
        return categoryDao.find(id);
    }

    public void deleteById(Long id) {
        categoryDao.delete(id);
    }

    public Category update(Category category) {
        return categoryDao.update(category);
    }

    public List<Category> findByName(String name) {
        return categoryDao.findByName(name);
    }
}
