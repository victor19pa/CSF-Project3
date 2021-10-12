package com.project3.ecommerce.controllers;

import com.project3.ecommerce.models.*;
import com.project3.ecommerce.services.implementations.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    private InvoiceServiceImpl invoiceServiceImpl;
    private InvoiceDetailsServiceImpl invoiceDetailsServiceImpl;
    private GuestServiceImpl guestServiceImpl;
    private PaymentTypeServiceImpl paymentTypeServiceImpl;
    private ProductServiceImpl productServiceImpl;
    private CategoryServiceImpl categoryServiceImpl;
    private Date date = new Date();

    List<Product> productList = new ArrayList();

    public HomeController(InvoiceServiceImpl invoiceServiceImpl, InvoiceDetailsServiceImpl invoiceDetailsServiceImpl, GuestServiceImpl guestServiceImpl, PaymentTypeServiceImpl paymentTypeServiceImpl, ProductServiceImpl productServiceImpl, CategoryServiceImpl categoryServiceImpl) {
        super();
        this.invoiceServiceImpl = invoiceServiceImpl;
        this.invoiceDetailsServiceImpl = invoiceDetailsServiceImpl;
        this.guestServiceImpl = guestServiceImpl;
        this.paymentTypeServiceImpl = paymentTypeServiceImpl;
        this.productServiceImpl = productServiceImpl;
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @GetMapping("/")
    public String getIndex(Model model){
        model.addAttribute("products",productServiceImpl.getAllProducts());
        return "Index";
    }

    @GetMapping("/product/{id}")
    public String productDetails(Model model,
                               @PathVariable(value = "id") Long id){
        model.addAttribute("product",productServiceImpl.getProductById(id));
        return "product";
    }

    @GetMapping("/shop/{category}")
    public String showCategory(Model model, @PathVariable String category){
        if (category.equals("All")){
            model.addAttribute("products",productServiceImpl.getAllProducts());
        }else{
            model.addAttribute("products",productServiceImpl.getProductsByCategory(category));
        }
        model.addAttribute("categories",categoryServiceImpl.getAllCategories());
        return "catalogue";
    }

    @GetMapping("/cart")
    public String showShoppingCart(Model model,
                                   RedirectAttributes redirectAttrs){
        double totalPrice = 0.0;
        if (productList.isEmpty()){
            redirectAttrs.addFlashAttribute("message", "The shopping cart is empty!");
        }else{
            for (int i = 0; i < productList.size(); i++){
                totalPrice += productList.get(i).getPrice() * productList.get(i).getQuantity();
            }
            model.addAttribute("products",productList);
            model.addAttribute("totalPrice",totalPrice);
        }
        return "shoppingCart";
    }

    @GetMapping("/addToCart/{id}/qty/{qty}")
    public String addToCart(@PathVariable(value = "id") Long id,
                            @PathVariable(value = "qty") int qty,
                            Model model){
        Product foundProduct = productServiceImpl.getProductById(id);
        if(productList.isEmpty()){
            foundProduct.setQuantity(qty);
            productList.add(foundProduct);
        }else{
            int pos = 0;
            for (int i = 0; i < productList.size(); i++){
                if (id == productList.get(i).getId()){
                    pos = i;
                }
            }
            if (id == productList.get(pos).getId()){
                productList.get(pos).setQuantity(productList.get(pos).getQuantity() + qty);
            }else{
                foundProduct.setQuantity(qty);
                productList.add(foundProduct);
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/removeFromCart/{id}/qty/{qty}")
    public String removeFromCart(@PathVariable(value = "id") Long id,
                            @PathVariable(value = "qty") int qty,
                            Model model){
        if(!productList.isEmpty()){
            int pos = 0;
            for (int i = 0; i < productList.size(); i++){
                if (id == productList.get(i).getId()){
                    pos = i;
                }
            }
            if (id == productList.get(pos).getId()){
                if (productList.get(pos).getQuantity() == 1){
                    productList.remove(productList.get(pos));
                }else{
                    productList.get(pos).setQuantity(productList.get(pos).getQuantity() - qty);
                }
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/payment")
    public String requestInformation(Model model){
        double totalPrice = 0.0;
        for (int i = 0; i < productList.size(); i++){
            totalPrice += productList.get(i).getPrice() * productList.get(i).getQuantity();
        }
        model.addAttribute("guest", new Guest());
        model.addAttribute("paymentType",new PaymentType());
        model.addAttribute("products",productList);
        model.addAttribute("totalPrice",totalPrice);
        return "payment";
    }

    @PostMapping("/saveInvoice")
    public String saveInvoice(@ModelAttribute Guest guest,
                              @ModelAttribute PaymentType paymentType,
                              RedirectAttributes redirectAttrs){

        double totalPrice = 0.0;
        for (int i = 0; i < productList.size(); i++){
            totalPrice += productList.get(i).getPrice() * productList.get(i).getQuantity();
        }

        paymentTypeServiceImpl.savePaymentType(paymentType);

        guestServiceImpl.saveGuest(guest);

        Invoice invoice = new Invoice();
        invoice.setDate(date);
        invoice.setStatus("ON PROCESS");
        invoice.setTotalOrder(totalPrice);
        invoice.setGuest(guest);
        invoice.setPaymentType(paymentType);
        Invoice createdInvoice = invoiceServiceImpl.saveInvoice(invoice);

        for(int i = 0; i < productList.size(); i++){
            InvoiceDetails invoiceDetails = new InvoiceDetails();
            invoiceDetails.setQuantity(productList.get(i).getQuantity());
            invoiceDetails.setSubTotal(productList.get(i).getQuantity()*productList.get(i).getPrice());
            invoiceDetails.setInvoice(createdInvoice);
            invoiceDetails.setProduct(productList.get(i));
            invoiceDetailsServiceImpl.saveInvoiceDetails(invoiceDetails);
        }
        productList.removeAll(productList);
        redirectAttrs.addFlashAttribute("message", "You have successfully purchased!, Thank you for preferring us");
        return "redirect:/";
    }
}
