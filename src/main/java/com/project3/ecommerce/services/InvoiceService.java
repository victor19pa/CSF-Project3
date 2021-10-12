package com.project3.ecommerce.services;

import com.project3.ecommerce.models.Invoice;

import java.util.List;

public interface InvoiceService {
    List<Invoice> getAllInvoices();
    Invoice getInvoiceById(Long id);
    Invoice saveInvoice(Invoice invoice);
    Invoice updateInvoice(Invoice invoice);
}
