package com.project3.ecommerce.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private MyInterceptor myInterceptor;

        @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/Dashboard").addPathPatterns("/Show/Categories")
                .addPathPatterns("/Create/Category").addPathPatterns("/Edit/Category/{id}").addPathPatterns("/Save/Category")
                .addPathPatterns("/Update/Category/{id}").addPathPatterns("/Create/PaymentType").addPathPatterns("/Save/PaymentType")
                .addPathPatterns("/Show/PaymentTypes").addPathPatterns("/Edit/PaymentType/{id}").addPathPatterns("/Update/PaymentType/{id}")
                .addPathPatterns("/Create/Product").addPathPatterns("/Save/Product").addPathPatterns("/Show/Products")
                .addPathPatterns("/Show/Invoices").addPathPatterns("Show/Invoice/Detail/{id}").addPathPatterns("/Edit/Product/{id}")
                .addPathPatterns("/Update/Product/{id}");
        WebMvcConfigurer.super.addInterceptors(registry);
    }


}
