package com.project3.ecommerce.services;

import com.project3.ecommerce.models.Category;
import com.project3.ecommerce.repositories.CategoryRepository;

import java.util.List;

public interface CategoryService{
    List<Category> getAllCategories();
    Category saveCategory(Category category);
    Category getCategoryById(Long id);
    Category updateCategory(Category category);
    void deleteCategoryById(Long id);
}
