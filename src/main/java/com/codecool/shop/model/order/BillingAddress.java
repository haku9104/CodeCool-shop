package com.codecool.shop.model.order;

import java.util.Locale;

public class BillingAddress {
    public String country, city, zipcode, address;
    public BillingAddress(String country, String city, String zipcode, String address) {
        this.country = country;
        this.city = city;
        this.zipcode = zipcode;
        this.address = address;
    }

    @Override
    public String toString(){
        return zipcode + " " + city + " " + address + ", " + country;
    }
}
