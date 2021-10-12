package com.project3.ecommerce.services;



import com.project3.ecommerce.models.Administrator;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdministratorService {
    Administrator saveAdministrator(Administrator administrator);
    boolean findMailAdministrator(String email);
    Administrator getAdministrator(String email);
    List<Administrator> findAllAdministrator();

}
