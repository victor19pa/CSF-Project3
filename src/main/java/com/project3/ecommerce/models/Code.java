package com.project3.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Code")
public class Code {
    @Id
    @Column(name = "code_id", nullable = false)
    private Long id;

    @Column(length = 255, nullable = false)
    private String code;
}
