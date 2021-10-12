package com.project3.ecommerce.controllers;

import com.project3.ecommerce.models.Invoice;
import com.project3.ecommerce.models.InvoiceDetails;
import com.project3.ecommerce.models.Product;
import com.project3.ecommerce.services.implementations.InvoiceDetailsServiceImpl;
import com.project3.ecommerce.services.implementations.InvoiceServiceImpl;
import com.project3.ecommerce.services.implementations.ProductServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/invoiceDetail")
public class InvoiceDetailController {

    private InvoiceDetailsServiceImpl invoiceDetailsServiceImpl;
    private InvoiceServiceImpl invoiceServiceImpl;
    private ProductServiceImpl productServiceImpl;

    public InvoiceDetailController(InvoiceDetailsServiceImpl invoiceDetailsServiceImpl, InvoiceServiceImpl invoiceServiceImpl, ProductServiceImpl productServiceImpl) {
        super();
        this.invoiceDetailsServiceImpl = invoiceDetailsServiceImpl;
        this.invoiceServiceImpl = invoiceServiceImpl;
        this.productServiceImpl = productServiceImpl;
    }

    @GetMapping("/show")
    public List<InvoiceDetails> showAllInvoiceDetails(){
        return invoiceDetailsServiceImpl.getAllInvoiceDetails();
    }

    @PostMapping("/save")
    public void saveInvoiceDetails(){
        Product product = productServiceImpl.getProductById(1L);
        Invoice invoice = invoiceServiceImpl.getInvoiceById(1L);

        InvoiceDetails invoiceDetails = new InvoiceDetails();
        invoiceDetails.setQuantity(2);
        invoiceDetails.setSubTotal(product.getPrice()*invoiceDetails.getQuantity());
        invoiceDetails.setInvoice(invoice);
        invoiceDetails.setProduct(product);

        invoiceDetailsServiceImpl.saveInvoiceDetails(invoiceDetails);
    }
}
