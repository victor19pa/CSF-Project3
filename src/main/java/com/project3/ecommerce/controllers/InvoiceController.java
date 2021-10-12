package com.project3.ecommerce.controllers;

import com.project3.ecommerce.models.*;
import com.project3.ecommerce.services.implementations.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    List<Product> productList = new ArrayList();

    private InvoiceServiceImpl invoiceServiceImpl;
    private InvoiceDetailsServiceImpl invoiceDetailsServiceImpl;
    private GuestServiceImpl guestServiceImpl;
    private PaymentTypeServiceImpl paymentTypeServiceImpl;
    private ProductServiceImpl productServiceImpl;
    private Date date = new Date();

    public InvoiceController(List<Product> productList, InvoiceServiceImpl invoiceServiceImpl, InvoiceDetailsServiceImpl invoiceDetailsServiceImpl, GuestServiceImpl guestServiceImpl, PaymentTypeServiceImpl paymentTypeServiceImpl, ProductServiceImpl productServiceImpl) {
        super();
        this.invoiceServiceImpl = invoiceServiceImpl;
        this.invoiceDetailsServiceImpl = invoiceDetailsServiceImpl;
        this.guestServiceImpl = guestServiceImpl;
        this.paymentTypeServiceImpl = paymentTypeServiceImpl;
        this.productServiceImpl = productServiceImpl;
    }

    @GetMapping("/show")
    public String showAllInvoices(Model model){
        model.addAttribute("invoices",invoiceServiceImpl.getAllInvoices());
        return "views/testing.html";
    }

    @PostMapping("/save/")
    @ResponseBody
    public void saveInvoice() {

        List<InvoiceDetails> invoiceDetailsList = new ArrayList<>();

        Guest guest = guestServiceImpl.getGuestById(1L);
        PaymentType paymentType = paymentTypeServiceImpl.getPaymentTypeById(1L);
        Product product = productServiceImpl.getProductById(1L);


        int cantidad = 1;
        int posicion = 0;

        Invoice invoice = new Invoice();
        invoice.setDate(this.date);
        invoice.setStatus("DELIVERED");
        invoice.setTotalOrder(210.12);
        invoice.setGuest(guest);
        invoice.setPaymentType(paymentType);
        Invoice invoiceAdd = invoiceServiceImpl.saveInvoice(invoice);

        //variables de interface
        int idp = 1;

        if(invoiceDetailsList.size()>0){
            for (int i=0; i<invoiceDetailsList.size(); i++){
                if(idp==invoiceDetailsList.get(i).getProduct().getId()){ //id producto que capture en interfaz , igual al product.getid
                    posicion=i;
                }
            }
            if(idp==invoiceDetailsList.get(posicion).getProduct().getId()){
                cantidad=invoiceDetailsList.get(posicion).getQuantity()+cantidad;
                double subTotal = invoiceDetailsList.get(posicion).getProduct().getPrice()*cantidad;
                invoiceDetailsList.get(posicion).setQuantity(cantidad);
                invoiceDetailsList.get(posicion).setSubTotal(subTotal);
            }
            else {
                InvoiceDetails invoiceDetails = new InvoiceDetails();
                invoiceDetails.setInvoice(invoiceAdd);
                invoiceDetails.setProduct(product);
                invoiceDetails.setQuantity(cantidad);
                invoiceDetails.setSubTotal(product.getPrice()*cantidad);

                invoiceDetailsServiceImpl.saveInvoiceDetails(invoiceDetails);
                invoiceDetailsList.add(invoiceDetails);
            }
        }else{
            InvoiceDetails invoiceDetails = new InvoiceDetails();
            invoiceDetails.setInvoice(invoiceAdd);
            invoiceDetails.setProduct(product);
            invoiceDetails.setQuantity(cantidad);
            invoiceDetails.setSubTotal(product.getPrice()*cantidad);

            invoiceDetailsServiceImpl.saveInvoiceDetails(invoiceDetails);
            invoiceDetailsList.add(invoiceDetails);
        }
    }
}
