package com.project3.ecommerce.services;

import com.project3.ecommerce.models.Invoice;
import com.project3.ecommerce.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product saveProduct(Product product);
    Product updateProduct(Product product);
}
