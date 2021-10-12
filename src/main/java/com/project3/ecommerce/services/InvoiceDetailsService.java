package com.project3.ecommerce.services;

import com.project3.ecommerce.models.Invoice;
import com.project3.ecommerce.models.InvoiceDetails;

import java.util.List;

public interface InvoiceDetailsService {
    List<InvoiceDetails> getAllInvoiceDetails();
    InvoiceDetails saveInvoiceDetails(InvoiceDetails invoiceDetails);
    InvoiceDetails getInvoiceDetailsById(Long id);
}
