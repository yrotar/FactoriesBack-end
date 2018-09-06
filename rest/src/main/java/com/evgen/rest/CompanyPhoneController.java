package com.evgen.rest;

import com.evgen.Company;
import com.evgen.Phone;
import com.evgen.json.View;
import com.evgen.rest.wrappers.IdWrapper;
import com.evgen.service.CompanyPhoneService;
import com.fasterxml.jackson.annotation.JsonView;
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
    CompanyPhoneService service;

    @Autowired
    public CompanyPhoneController(CompanyPhoneService service) {
        this.service = service;
    }

    @JsonView(View.CompanySummary.class)
    @GetMapping("/companies")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Company> getCompanies(@RequestParam(value = "name", required = false) String name,
                                      @RequestParam(value = "minEmployees", required = false) Integer minEmployees,
                                      @RequestParam(value = "maxEmployees", required = false) Integer maxEmployees) {
        if (!StringUtils.isEmpty(name)) {
            return Collections.singletonList(service.getCompanyByName(name));
        }

        return service.getCompanies(minEmployees, maxEmployees);
    }

    @JsonView(View.CompanyDetails.class)
    @GetMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Company getCompanyById(@PathVariable(value = "id") Integer id) {
        return service.getCompanyById(id);
    }

    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public IdWrapper addCompany(@RequestBody Company company) {
        return IdWrapper.wrap(service.addCompany(company));
    }

    @PutMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public void updateCompany(@RequestBody Company company, @PathVariable("id") Integer id) {
        company.setCompanyId(id);

        service.updateCompany(company);
    }

    @DeleteMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCompany(@PathVariable(value = "id") Integer id) {
        service.deleteCompany(id);
    }

    @JsonView(View.PhoneSummary.class)
    @GetMapping("/phones")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Phone> getPhones(@RequestParam(value = "minPrice", required = false) Integer minPrice,
                                 @RequestParam(value = "maxPrice", required = false) Integer maxPrice) {
        return service.getPhones(minPrice, maxPrice);
    }

    @JsonView(View.PhoneDetails.class)
    @GetMapping("/phones/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Phone getPhoneById(@PathVariable(value = "id") Integer id) {
        return service.getPhoneById(id);
    }

    @PostMapping("/phones")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public IdWrapper addPhone(@RequestBody Phone phone) {
        return IdWrapper.wrap(service.addPhone(phone));
    }

    @PutMapping("/phones/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public void updatePhone(@RequestBody Phone phone, @PathVariable("id") Integer id) {
        phone.setPhoneId(id);
        service.updatePhone(phone);
    }

    @DeleteMapping("/phones/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePhone(@PathVariable(value = "id") Integer id) {
        service.deletePhone(id);
    }
}