package com.evgen;

import com.evgen.json.View;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Phone {

    @JsonView({
            View.PhoneSummary.class,
            View.CompanyDetails.class
    })
    private Integer phoneId;

    @JsonView({
            View.PhoneSummary.class,
            View.CompanyDetails.class
    })
    private String name;

    @JsonView({
            View.PhoneSummary.class,
            View.CompanyDetails.class
    })
    private Integer price;

    @JsonView(View.PhoneSummary.class)
    private Company company;

    public Phone() {
    }

    public Phone(Integer phoneId, String name, Integer price) {
        this.phoneId = phoneId;
        this.name = name;
        this.price = price;
    }

    public Phone(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public Phone(String name, Integer price, Company company) {
        this.name = name;
        this.price = price;
        this.company = company;
    }

    public Phone(Integer phoneId, String name, Integer price, Company company) {
        this.phoneId = phoneId;
        this.name = name;
        this.price = price;
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}