package com.project3.ecommerce.Code;

import com.project3.ecommerce.services.implementations.CodeServiceImpl;

public class CodeClass {

    public static   String getCodeValue(CodeServiceImpl codeServiceImpl ){
        return codeServiceImpl.findKey(Long.parseLong(String.valueOf(0))).getCode();
    }
}
