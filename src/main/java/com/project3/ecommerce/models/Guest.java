package com.project3.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Guest")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id", nullable = false)
    private Long id;

    @Column(nullable = false,length = 75)
    private String email;

    @Column(nullable = false,length = 50)
    private String name;

    @Column(nullable = false,length = 250)
    private String address;

    @OneToMany(mappedBy = "guest", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<Invoice> invoices;
}
