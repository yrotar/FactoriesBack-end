package com.evgen.rest;


import com.evgen.Company;
import com.evgen.ServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CompanyPhoneController {

    @Autowired
    ServiceApi service;

    @GetMapping(value = "/companies/{id}")
    public Company getCompanyById(@PathVariable(value = "id") Integer id) {

        return service.getCompanyById(id);
    }
}
