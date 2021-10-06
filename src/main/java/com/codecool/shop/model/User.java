package com.codecool.shop.model;

import com.codecool.shop.model.order.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class User {

    private int id;
    private String name;
    private String fullName;

    private String email = "";
    private String phone = "";
    private String billing_street = "";
    private String billing_city = "";
    private String billing_country = "";
    private String billing_zip = "";
    private String shipping_street = "";
    private String shipping_city = "";
    private String shipping_country = "";
    private String shipping_zip = "";

    private boolean dataChanged;
    private boolean cartError;
    private boolean loginError;

    private static User instance;
    private HashMap<Product, Integer> shoppingCart;
    private Order newOrder;

    private User() {}

    public static User getInstance() {
        if(instance == null) {
            instance = new User();
            instance.setId(1);
            instance.setName("Bela");
            instance.setShoppingCart(new HashMap<>());
        }
        return instance;
    }

    public Order getNewOrder() {
        return newOrder;
    }

    public void setNewOrder(Order newOrder) {
        this.newOrder = newOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(HashMap<Product, Integer> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void add(Product product) {
        if(shoppingCart.containsKey(product))
            shoppingCart.put(product, shoppingCart.get(product)+1);
        else
            shoppingCart.put(product, 1);
    }

    public int cartSum() {
        return shoppingCart.values().stream().mapToInt(Integer::intValue).sum();
    }

    public float cartSumPrice() {
        float sum = 0;
        for(Product p: shoppingCart.keySet())
            sum += p.getFloatPrice() * shoppingCart.get(p);
        return sum;
    }

    public void updateCart(int productId, int quantity) {
        for(Product product : shoppingCart.keySet()) {
            if(product.getId() == productId) {
                if(quantity == 0)
                    shoppingCart.remove(product);
                else
                    shoppingCart.put(product, quantity);
            }
        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBilling_street() {
        return billing_street;
    }

    public void setBilling_street(String billing_street) {
        this.billing_street = billing_street;
    }

    public String getBilling_city() {
        return billing_city;
    }

    public void setBilling_city(String billing_city) {
        this.billing_city = billing_city;
    }

    public String getBilling_country() {
        return billing_country;
    }

    public void setBilling_country(String billing_country) {
        this.billing_country = billing_country;
    }

    public String getBilling_zip() {
        return billing_zip;
    }

    public void setBilling_zip(String billing_zip) {
        this.billing_zip = billing_zip;
    }

    public String getShipping_street() {
        return shipping_street;
    }

    public void setShipping_street(String shipping_street) {
        this.shipping_street = shipping_street;
    }

    public String getShipping_city() {
        return shipping_city;
    }

    public void setShipping_city(String shipping_city) {
        this.shipping_city = shipping_city;
    }

    public String getShipping_country() {
        return shipping_country;
    }

    public void setShipping_country(String shipping_country) {
        this.shipping_country = shipping_country;
    }

    public String getShipping_zip() {
        return shipping_zip;
    }

    public void setShipping_zip(String shipping_zip) {
        this.shipping_zip = shipping_zip;
    }

    public boolean isDataChanged() {
        return dataChanged;
    }

    public void setDataChanged(boolean dataChanged) {
        this.dataChanged = dataChanged;
    }

    public boolean isCartError() {
        return cartError;
    }

    public void setCartError(boolean cartError) {
        this.cartError = cartError;
    }

    public boolean isLoginError() {
        return loginError;
    }

    public void setLoginError(boolean loginError) {
        this.loginError = loginError;
    }
}
