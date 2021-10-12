package com.project3.ecommerce.controllers;

import com.project3.ecommerce.models.Category;
import com.project3.ecommerce.services.implementations.CategoryServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryServiceImpl categoryServiceImpl;

    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        super();
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @GetMapping("/show")
    public List<Category> showAllCategories(){
        return categoryServiceImpl.getAllCategories();
    }

    @GetMapping("/show/active")
    public List<Category> showActiveCategories(){
        return categoryServiceImpl.getActiveCategories("Active");
    }

//    @GetMapping("/show")
//    public String showCategories(Model model){
//        model.addAttribute("categories",categoryServiceImpl.getAllCategories());
//        return "views/showCategories.html";
//    }

    @PostMapping("/save")
    @ResponseBody
    public Category saveCategory(@ModelAttribute Category category){
        return categoryServiceImpl.saveCategory(category);
    }

    @PutMapping("/update/{id}")
    public String updateCategory(
            @PathVariable(value = "id") Long categoryId,
            @ModelAttribute Category category,
            RedirectAttributes redirectAttrs)
    {
        Category foundCategory = categoryServiceImpl.getCategoryById(categoryId);
        foundCategory.setName(category.getName());
        foundCategory.setStatus(category.getStatus());
        categoryServiceImpl.saveCategory(foundCategory);
        redirectAttrs.addFlashAttribute("message", "Category was updated successfully!");
        return "redirect:/CreateCategory";
    }
}
