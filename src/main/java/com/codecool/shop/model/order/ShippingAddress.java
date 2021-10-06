package com.codecool.shop.model.order;

public class ShippingAddress {
    public String country, city, zipcode, address;
    public ShippingAddress(String country, String city, String zipcode, String address) {
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