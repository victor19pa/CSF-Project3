package com.project3.ecommerce.services;

import com.project3.ecommerce.models.PaymentType;

import java.util.List;

public interface PaymentTypeService {
    List<PaymentType> getAllPaymentTypes();
    PaymentType getPaymentTypeById(Long id);
    PaymentType savePaymentType(PaymentType paymentType);
    PaymentType updatePaymentType(PaymentType paymentType);
    void deletePaymentTypeById(Long id);
}
