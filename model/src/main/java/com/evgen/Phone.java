package com.evgen;

public class Phone {
    private Integer phoneId;
    private String name;
    private Integer price;
    private Integer companyId;
    //private String companyName;

    public Phone() {
    }

    public Phone(Integer phoneId, String name, Integer price, Integer companyId) {
        this.phoneId = phoneId;
        this.name = name;
        this.price = price;
        this.companyId = companyId;
    }

    public Phone(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public Phone(String name, Integer price, Integer companyId) {
        this.name = name;
        this.price = price;
        this.companyId = companyId;
    }

//    public Phone(Integer phoneId, String name, Integer price, Integer companyId, String companyName) {
//        this.phoneId = phoneId;
//        this.name = name;
//        this.price = price;
//        this.companyId = companyId;
//        //this.companyName = companyName;
//    }

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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

//    public String getCompanyName() {
//        return companyName;
//    }

//    public void setCompanyName(String companyName) {
//        this.companyName = companyName;
//    }
}