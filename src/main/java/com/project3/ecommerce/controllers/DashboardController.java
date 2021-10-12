package com.project3.ecommerce.controllers;

import com.project3.ecommerce.models.Administrator;
import com.project3.ecommerce.models.Category;
import com.project3.ecommerce.models.PaymentType;
import com.project3.ecommerce.models.Product;
import com.project3.ecommerce.security.SessionSingleton;
import com.project3.ecommerce.services.UploadImageService;
import com.project3.ecommerce.services.implementations.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class DashboardController {

    private CategoryServiceImpl categoryServiceImpl;
    private PaymentTypeServiceImpl paymentTypeServiceImpl;
    private ProductServiceImpl productServiceImpl;
    private InvoiceServiceImpl invoiceServiceImpl;
    private InvoiceDetailsServiceImpl invoiceDetailsServiceImpl;
    private UploadImageService uploadImageService;
    private AdministratorServiceImplementation administratorServiceImplementation;

    public DashboardController(CategoryServiceImpl categoryServiceImpl, PaymentTypeServiceImpl paymentTypeServiceImpl, ProductServiceImpl productServiceImpl, InvoiceServiceImpl invoiceServiceImpl, InvoiceDetailsServiceImpl invoiceDetailsServiceImpl, UploadImageService uploadImageService, AdministratorServiceImplementation administratorServiceImplementation) {
        super();
        this.categoryServiceImpl = categoryServiceImpl;
        this.paymentTypeServiceImpl = paymentTypeServiceImpl;
        this.productServiceImpl = productServiceImpl;
        this.invoiceServiceImpl = invoiceServiceImpl;
        this.invoiceDetailsServiceImpl = invoiceDetailsServiceImpl;
        this.uploadImageService = uploadImageService;
        this.administratorServiceImplementation = administratorServiceImplementation;
    }

    @GetMapping("/Dashboard")
    public String dashboard(Model model) {
        model.addAttribute("administrator", SessionSingleton.getSessionAccount());
        return "views/admin/dashboard";
    }

    //CATEGORIES
    @GetMapping("/Show/Categories")
    public String showCategories(Model model){
        model.addAttribute("categories",categoryServiceImpl.getAllCategories());
        return "views/admin/ShowCategories";
    }

    @GetMapping("/Create/Category")
    public String createCategory(Model model){
        model.addAttribute("category",new Category());
        return "views/admin/CreateCategory";
    }

    @GetMapping("/Edit/Category/{id}")
    public String showFormEditCategory(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("category",categoryServiceImpl.getCategoryById(id));
        return "views/admin/EditCategory";
    }

    @PostMapping(value = "/Save/Category")
    public String saveCategory(@ModelAttribute Category category, RedirectAttributes redirectAttrs) {
        categoryServiceImpl.saveCategory(category);
        redirectAttrs.addFlashAttribute("message", "Category added successfully!");
        return "redirect:/Create/Category";
    }

    @PostMapping("/Update/Category/{id}")
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
        return "redirect:/Create/Category";
    }

    //PAYMENT TYPE
    @GetMapping("/Create/PaymentType")
    public String createPaymentType(Model model){
        model.addAttribute("paymentType",new PaymentType());
        return "views/admin/CreatePaymentType";
    }

    @PostMapping(value = "/Save/PaymentType")
    public String savePaymentType(@ModelAttribute PaymentType paymentType, RedirectAttributes redirectAttrs) {
        paymentTypeServiceImpl.savePaymentType(paymentType);
        redirectAttrs.addFlashAttribute("message", "Payment type added successfully!");
        return "redirect:/Create/PaymentType";
    }

    @GetMapping("/Show/PaymentTypes")
    public String showPaymentTypes(Model model){
        model.addAttribute("paymentTypes",paymentTypeServiceImpl.getAllPaymentTypes());
        return "views/admin/ShowPaymentType";
    }

    @GetMapping("/Edit/PaymentType/{id}")
    public String showFormEditPaymentType(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("paymentType",paymentTypeServiceImpl.getPaymentTypeById(id));
        return "views/admin/EditPaymentType";
    }

    @PostMapping("/Update/PaymentType/{id}")
    public String updatePaymentType(
            @PathVariable(value = "id") Long id,
            @ModelAttribute PaymentType paymentType,
            RedirectAttributes redirectAttrs)
    {
        PaymentType foundPaymentType = paymentTypeServiceImpl.getPaymentTypeById(id);
        foundPaymentType.setType(paymentType.getType());
        paymentTypeServiceImpl.savePaymentType(foundPaymentType);
        redirectAttrs.addFlashAttribute("message", "Payment type was updated successfully!");
        return "redirect:/Create/PaymentType";
    }

    //PRODUCT
    @GetMapping("/Create/Product")
    public String createProduct(Model model){
        model.addAttribute("product",new Product());
        model.addAttribute("categories",categoryServiceImpl.getAllCategories());
        return "views/admin/CreateProduct";
    }

    @PostMapping(value = "/Save/Product")
    public String saveProduct(@ModelAttribute Product product,
                              @RequestParam("file") MultipartFile file,
                              RedirectAttributes redirectAttrs) throws IOException {
        if (!file.isEmpty()){
            uploadImageService.saveFile(file);
            product.setImage(file.getOriginalFilename());
            productServiceImpl.saveProduct(product);
            redirectAttrs.addFlashAttribute("message", "Product added successfully!");
        }else{
            redirectAttrs.addFlashAttribute("message", "You must add an image");
        }
        return "redirect:/Create/Product";
    }

    @GetMapping("/Show/Products")
    public String showProducts(Model model){
        model.addAttribute("products",productServiceImpl.getAllProducts());
        return "views/admin/ShowProducts";
    }

    @GetMapping("/Show/Invoices")
    public String showInvoices(Model model, Long id){
        model.addAttribute("invoiceDetails", invoiceDetailsServiceImpl.getAllInvoiceDetails());
        model.addAttribute("invoices", invoiceServiceImpl.getAllInvoices());
        return "views/admin/ShowInvoices";
    }

    @GetMapping("Show/Invoice/Detail/{id}")
    public String showInvoiceDetails(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("invoices", invoiceServiceImpl.getInvoiceById(id));
        return "views/admin/ShowInvoiceDetail";
    }

    @GetMapping("/Edit/Product/{id}")
    public String showFormEditProduct(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("product",productServiceImpl.getProductById(id));
        model.addAttribute("categories",categoryServiceImpl.getAllCategories());
        return "views/admin/EditProduct";
    }

    @PostMapping("/Update/Product/{id}")
    public String updateProduct(
            @PathVariable(value = "id") Long id,
            @ModelAttribute Product product,
            RedirectAttributes redirectAttrs)
    {
        Product foundProduct = productServiceImpl.getProductById(id);
        foundProduct.setName(product.getName());
        foundProduct.setCategory(product.getCategory());
        foundProduct.setStock(product.getStock());
        foundProduct.setPrice(product.getPrice());
        foundProduct.setStatus(product.getStatus());
        foundProduct.setImage(foundProduct.getImage());
        foundProduct.setDescription(product.getDescription());
        productServiceImpl.saveProduct(foundProduct);
        redirectAttrs.addFlashAttribute("message", "Product was updated successfully!");
        return "redirect:/Create/Product";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("administrator", new Administrator());
        return "login";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("administrator", new Administrator());
        return "register";
    }

    @GetMapping("/show/users")
    public String showUsers(Model model) {
        model.addAttribute("accounts", administratorServiceImplementation.findAllAdministrator());
        return "views/admin/Showaccounts";
    }
}
