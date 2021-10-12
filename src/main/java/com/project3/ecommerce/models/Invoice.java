package com.project3.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Double totalOrder;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<InvoiceDetails> invoiceDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_type_id")
    @JsonBackReference
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id")
    @JsonBackReference
    private Guest guest;
}
