package com.project3.ecommerce.services.implementations;

import com.project3.ecommerce.models.Code;
import com.project3.ecommerce.repositories.CodeRepository;
import com.project3.ecommerce.services.CodeService;
import org.springframework.stereotype.Service;

@Service
public class CodeServiceImpl implements CodeService {
    private final CodeRepository codeRepository;

    public CodeServiceImpl(CodeRepository codeRepository) {
        super();
        this.codeRepository = codeRepository;
    }
    @Override
    public Code findKey(Long id) {
       return  codeRepository.findById(id).get();
    }
}
