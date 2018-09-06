package com.evgen;

import com.evgen.json.View;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Company {

    @JsonView(View.CompanySummary.class)
    private Integer companyId;

    @JsonView({
            View.CompanySummary.class,
            View.PhoneSummary.class
    })
    private String name;

    @JsonView(View.CompanySummary.class)
    private Integer employees;

    @JsonView(View.CompanySummary.class)
    private Float avgPrice;

    @JsonView(View.CompanyDetails.class)
    private List<Phone> phones;

    public Company() {
    }

    public Company(Integer companyId, String name) {
        this.companyId = companyId;
        this.name = name;
    }

    public Company(String name, Integer employees) {
        this.name = name;
        this.employees = employees;
    }

    public Company(Integer companyId, String name, Integer employees) {
        this.companyId = companyId;
        this.name = name;
        this.employees = employees;
    }

    public Company(Integer companyId, String name, Integer employees, Float avgPrice) {
        this.companyId = companyId;
        this.name = name;
        this.employees = employees;
        this.avgPrice = avgPrice;
    }

    public Company(Integer companyId, String name, Integer employees, Float avgPrice, List<Phone> phones) {
        this.companyId = companyId;
        this.name = name;
        this.employees = employees;
        this.avgPrice = avgPrice;
        this.phones = phones;
    }

    public Float getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Float avgPrice) {
        this.avgPrice = avgPrice;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEmployees() {
        return employees;
    }

    public void setEmployees(Integer employees) {
        this.employees = employees;
    }
}