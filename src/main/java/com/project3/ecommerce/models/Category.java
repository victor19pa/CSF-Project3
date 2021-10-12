package com.project3.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long id;

    @Column(name = "Name", length = 25)
    private String name;

    @Column(name = "Status", length = 15)
    private String status;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY,
            orphanRemoval = true)
    @JsonManagedReference
    private List<Product> products;

}
