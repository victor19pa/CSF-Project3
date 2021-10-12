package com.project3.ecommerce;

import com.project3.ecommerce.models.Category;
import com.project3.ecommerce.repositories.CategoryRepository;
import com.project3.ecommerce.services.implementations.CategoryServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class TestsCategory {
    @Autowired
    private CategoryRepository repo;
    private CategoryServiceImpl serviceImpl;
    @Test
    public void testSaveCategory() {
        Category category = new Category();

        category.setName("JUnitName2");
        category.setStatus("JunitStatus2");

        Category savedCategory = repo.save(category);
        //Category savedCategory = serviceImpl.saveCategory(category);

        Assertions.assertThat(savedCategory).isNotNull();
        Assertions.assertThat(savedCategory.getId()).isGreaterThan(0);
    }
    @Test
    public void testListCategory(){
        Iterable<Category> categories = repo.findAll();
        Assertions.assertThat(categories).hasSizeGreaterThan(0);
        for (Category category : categories) {
            System.out.println(category);
        }
    }
    @Test
    public void testUpdateCategory(){
        Integer catId = 1;
        Long categoryId = Long.valueOf(catId);
        Optional<Category> optionalCategory = repo.findById(categoryId);
        Category category = optionalCategory.get();

        category.setName("testUpdate");
        repo.save(category);

        Category updateCategory = repo.findById(categoryId).get();

        Assertions.assertThat(updateCategory.getName()).isEqualTo("testUpdate");
    }
    @Test
    public void testDeleteCategory(){
        Integer catId = 2;
        Long categoryId = Long.valueOf(catId);

        repo.deleteById(categoryId);

        Optional<Category> optionalUser = repo.findById(categoryId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
    @Test
    public void testGetCategory(){
        Integer catId = 1;
        Long categoryId = Long.valueOf(catId);
        Optional<Category> optionalCategory= repo.findById(categoryId);

        Assertions.assertThat(optionalCategory).isPresent();
        System.out.println(optionalCategory.get());
    }

}
