package com.project3.ecommerce.services.implementations;

import com.project3.ecommerce.models.Invoice;
import com.project3.ecommerce.models.Product;
import com.project3.ecommerce.repositories.ProductRepository;
import com.project3.ecommerce.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.getProductByCategory_Name(category);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
}
