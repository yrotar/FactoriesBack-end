package com.evgen;

import java.util.List;

public class Company {

    private Integer companyId;
    private String name;
    private Integer employees;
    private List<Phone> phones;

    public Company() {
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

    public Company(Integer companyId, String name, Integer employeesCount, List<Phone> phones) {
        this.companyId = companyId;
        this.name = name;
        this.employees = employeesCount;
        this.phones = phones;
    }

    public List<Phone> getPhones() {
        return phones;
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