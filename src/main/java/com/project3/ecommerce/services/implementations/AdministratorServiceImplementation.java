package com.project3.ecommerce.services.implementations;



import com.project3.ecommerce.models.Administrator;
import com.project3.ecommerce.repositories.AdministratorRepository;
import com.project3.ecommerce.services.AdministratorService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImplementation implements AdministratorService {

    private final AdministratorRepository administratorRepository;

    public AdministratorServiceImplementation(AdministratorRepository administratorRepository) {
        super();
        this.administratorRepository = administratorRepository;
    }

    @Override
    public Administrator saveAdministrator(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    @Override
    public boolean findMailAdministrator(String email) {
        List<Administrator> list = administratorRepository.findAll();
        for (Administrator obj: list         ) {
            if (obj.getEmail().equals(email)){
                return true;

            }
        }
       return false;
    }



    @Override
    public Administrator getAdministrator(String email) {
        List<Administrator> list = administratorRepository.findAll();
        for (Administrator obj: list         ) {
            if (obj.getEmail().equals(email)){
                return obj;

            }
        }
        return null;
    }

    @Override
    public List<Administrator> findAllAdministrator() {
        return administratorRepository.findAll();
    }
}
