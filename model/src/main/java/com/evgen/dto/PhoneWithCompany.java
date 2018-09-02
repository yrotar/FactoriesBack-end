package com.evgen.dto;

import com.evgen.Phone;

public class PhoneWithCompany extends Phone {

    private String companyName;

    public PhoneWithCompany(Integer phoneId, String name, Integer price, Integer companyId, String companyName) {
        super(phoneId, name, price, companyId);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
