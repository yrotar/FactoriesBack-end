package com.evgen;

public class Company {

    private Integer companyId;
    private String name;
    private Integer employees;

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
