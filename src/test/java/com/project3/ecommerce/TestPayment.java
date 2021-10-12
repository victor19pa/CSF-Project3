package com.project3.ecommerce;

import com.project3.ecommerce.models.Category;
import com.project3.ecommerce.models.PaymentType;
import com.project3.ecommerce.repositories.CategoryRepository;
import com.project3.ecommerce.repositories.PaymentTypeRepository;
import com.project3.ecommerce.services.implementations.CategoryServiceImpl;
import com.project3.ecommerce.services.implementations.PaymentTypeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class TestPayment {
    @Autowired
    private PaymentTypeRepository repo;
    private PaymentTypeServiceImpl paymentTypeServiceImpl;
    @Test
    public void testSavePayment() {
        PaymentType paymentType = new PaymentType();

        paymentType.setType("Credit/Debit");

        PaymentType savedPayment= repo.save(paymentType);
        //Category savedCategory = serviceImpl.saveCategory(category);

        Assertions.assertThat(savedPayment).isNotNull();
        Assertions.assertThat(savedPayment.getId()).isGreaterThan(0);
    }
    @Test
    public void testLisPayment(){
        Iterable<PaymentType> payments = repo.findAll();
        Assertions.assertThat(payments).hasSizeGreaterThan(0);
        for (PaymentType paymentType : payments) {
            System.out.println(paymentType);
        }
    }
    @Test
    public void testUpdatePayment(){
        Integer payId = 1;
        Long paymentId = Long.valueOf(payId);
        Optional<PaymentType> optionalPayment = repo.findById(paymentId);
        PaymentType paymentType = optionalPayment.get();

        paymentType.setType("Change");
        repo.save(paymentType);

        PaymentType updatePayment= repo.findById(paymentId).get();

        Assertions.assertThat(updatePayment.getType()).isEqualTo("Change");
    }
    @Test
    public void testGetPayment(){
        Integer payId = 1;
        Long paymentId = Long.valueOf(payId);
        Optional<PaymentType> optionalPayment= repo.findById(paymentId);

        Assertions.assertThat(optionalPayment).isPresent();
        System.out.println(optionalPayment.get());
    }

}
