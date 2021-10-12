package com.project3.ecommerce.controllers;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.project3.ecommerce.models.Category;
import com.project3.ecommerce.models.Product;
import com.project3.ecommerce.services.implementations.CategoryServiceImpl;
import com.project3.ecommerce.services.implementations.ProductServiceImpl;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductServiceImpl productServiceImpl;
    private CategoryServiceImpl categoryServiceImpl;

    public ProductController(ProductServiceImpl productService, CategoryServiceImpl categoryService) {
        super();
        this.productServiceImpl = productService;
        this.categoryServiceImpl = categoryService;
    }

    @GetMapping("/show")
    public List<Product> showAllProducts(){
        return productServiceImpl.getAllProducts();
    }

    @PostMapping("/save")
    @ResponseBody
    public void saveProduct(){
        Category category = new Category();

        category = categoryServiceImpl.getCategoryById(1L);

        Product product = new Product();
        product.setStock(3);
        product.setName("Camisa Deportiva");
        product.setPrice(300.33);
        product.setImage("URL IMG");
        product.setCategory(category);
        product.setDescription("Camisa Algodon Deportiva");

        productServiceImpl.saveProduct(product);

    }

    @RequestMapping(path = "/")
    public String getOrderPage(Model model) throws IOException {
        List<Product> listProductOrder = productServiceImpl.getAllProducts();
        model.addAttribute("orderEntry", listProductOrder);
        return "order";
    }

}
