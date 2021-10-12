package com.project3.ecommerce;

import com.project3.ecommerce.models.Category;
import com.project3.ecommerce.models.Product;
import com.project3.ecommerce.repositories.CategoryRepository;
import com.project3.ecommerce.repositories.ProductRepository;
import com.project3.ecommerce.services.implementations.CategoryServiceImpl;
import com.project3.ecommerce.services.implementations.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class TestProduct {

    @Autowired
    private ProductRepository repo;
    private CategoryServiceImpl categoryImpl;
    private ProductServiceImpl serviceImpl;
    @Test
    public void testSaveCategory() {
        Product product = new Product();

        Category category = new Category();

        //category = categoryImpl.getCategoryById(1L);

        category.setName("asdaa");
        category.setStatus("Actuve");


        product.setCategory(category);
        product.setPrice(559.99);
        product.setImage("URL");
        product.setStock(2);
        product.setDescription("test desc");
        product.setName("test");
        product.setStatus("Active");


        Product savedProduct = repo.save(product);
        //Category savedCategory = serviceImpl.saveCategory(category);

        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getId()).isGreaterThan(0);
    }
    @Test
    public void testListCategory(){
        Iterable<Product> products = repo.findAll();
        Assertions.assertThat(products).hasSizeGreaterThan(0);
        for (Product product : products) {
            System.out.println(product);
        }
    }

}
