package com.evgen.rest;

import com.evgen.Company;
import com.evgen.Phone;
import com.evgen.service.ServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
public class CompanyPhoneController {

    private final
    ServiceApi service;

    @Autowired
    public CompanyPhoneController(ServiceApi service) {
        this.service = service;
    }

    @GetMapping(value = "/companies")
    public
    @ResponseBody
    List<? extends Company> getCompanies(@RequestParam(value = "name", required = false) String name) {

        if (!StringUtils.isEmpty(name)){
            return Collections.singletonList(service.getCompanyByName(name));
        }

        return service.getCompanies();
    }

    @GetMapping(value = "/companies/{id}")
    public
    @ResponseBody
    Company getCompanyById(@PathVariable(value = "id") Integer id) {

        return service.getCompanyById(id);
    }

    @RequestMapping(value = "/companies", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    Integer addCompany(@RequestBody Company company) {

        return service.addCompany(company);
    }

    @RequestMapping(value = "/companies/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public
    @ResponseBody
    Integer updateCompany(@RequestBody Company company, @PathVariable("id") Integer id) {
        company.setCompanyId(id);

        return service.updateCompany(company);
    }

    @RequestMapping(value = "/companies/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCompany(@PathVariable(value = "id") Integer id) {

        service.deleteCompany(id);
    }

    @GetMapping(value = "/phones")
    public
    @ResponseBody
    List<? extends Phone> getPhones() {
        return service.getPhones();
    }

    @GetMapping(value = "/phones/{id}")
    public
    @ResponseBody
    Phone getPhoneById(@PathVariable(value = "id") Integer id) {

        return service.getPhoneById(id);
    }

    @RequestMapping(value = "/phones", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    Integer addPhone(@RequestBody Phone phone) {

        return service.addPhone(phone);
    }

    @RequestMapping(value = "/phones/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public
    @ResponseBody
    Integer updatePhone(@RequestBody Phone phone, @PathVariable("id") Integer id) {
        phone.setPhoneId(id);

        return service.updatePhone(phone);
    }

    @RequestMapping(value = "/phones/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePhone(@PathVariable(value = "id") Integer id) {

        service.deletePhone(id);
    }
}