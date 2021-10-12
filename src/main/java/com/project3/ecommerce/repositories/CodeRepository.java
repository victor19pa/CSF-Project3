package com.project3.ecommerce.repositories;

import com.project3.ecommerce.models.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends JpaRepository<Code,Long> {
}
