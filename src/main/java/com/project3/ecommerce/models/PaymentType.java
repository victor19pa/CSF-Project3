package com.project3.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "PaymentType")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_type_id", nullable = false)
    private Long id;

    @Column(nullable = false,length = 25)
    private String type;

    @OneToMany(mappedBy = "paymentType", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<Invoice> invoices;
}
