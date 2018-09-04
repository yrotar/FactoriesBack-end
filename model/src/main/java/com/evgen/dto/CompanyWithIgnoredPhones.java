package com.evgen.dto;

import com.evgen.Company;
import com.evgen.Phone;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class CompanyWithIgnoredPhones extends Company {

    private Float avgPrice;

    public CompanyWithIgnoredPhones(Integer companyId, String name, Integer employees, Float avgPrice) {
        super(companyId, name, employees);
        this.avgPrice = avgPrice;
    }

    public Float getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Float avgPrice) {
        this.avgPrice = avgPrice;
    }

    public CompanyWithIgnoredPhones(Integer companyId, String name, Integer employees) {
        super(companyId, name, employees);
    }

    @JsonIgnore
    @Override
    public List<Phone> getPhones() {
        return super.getPhones();
    }
}
