package com.project3.ecommerce.services.implementations;

import com.project3.ecommerce.models.PaymentType;
import com.project3.ecommerce.repositories.PaymentTypeRepository;
import com.project3.ecommerce.services.PaymentTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService {

    private PaymentTypeRepository paymentTypeRepository;

    public PaymentTypeServiceImpl(PaymentTypeRepository paymentTypeRepository) {
        super();
        this.paymentTypeRepository = paymentTypeRepository;
    }

    @Override
    public List<PaymentType> getAllPaymentTypes() {
        return paymentTypeRepository.findAll();
    }

    @Override
    public PaymentType getPaymentTypeById(Long id) {
        return paymentTypeRepository.findById(id).get();
    }

    @Override
    public PaymentType savePaymentType(PaymentType paymentType) {
        return paymentTypeRepository.save(paymentType);
    }

    @Override
    public PaymentType updatePaymentType(PaymentType paymentType) {
        return paymentTypeRepository.save(paymentType);
    }

    @Override
    public void deletePaymentTypeById(Long id) {
        paymentTypeRepository.deleteById(id);
    }
}
