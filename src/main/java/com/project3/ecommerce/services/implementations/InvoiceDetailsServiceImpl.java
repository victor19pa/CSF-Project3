package com.project3.ecommerce.services.implementations;

import com.project3.ecommerce.models.Invoice;
import com.project3.ecommerce.models.InvoiceDetails;
import com.project3.ecommerce.repositories.InvoiceDetailsRepository;
import com.project3.ecommerce.services.InvoiceDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceDetailsServiceImpl implements InvoiceDetailsService{

    private InvoiceDetailsRepository invoiceDetailsRepository;

    public InvoiceDetailsServiceImpl(InvoiceDetailsRepository invoiceDetailsRepository) {
        super();
        this.invoiceDetailsRepository = invoiceDetailsRepository;
    }

    @Override
    public List<InvoiceDetails> getAllInvoiceDetails() {
        return invoiceDetailsRepository.findAll();
    }

    @Override
    public InvoiceDetails saveInvoiceDetails(InvoiceDetails invoiceDetails) {
        return invoiceDetailsRepository.save(invoiceDetails);
    }

    @Override
    public InvoiceDetails getInvoiceDetailsById(Long id) {
        return invoiceDetailsRepository.findById(id).get();
    }

}
