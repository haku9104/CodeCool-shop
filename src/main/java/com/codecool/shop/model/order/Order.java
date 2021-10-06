package com.codecool.shop.model.order;

import com.codecool.shop.model.Payment;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;

import java.util.Map;

public class Order {

    private String filename;
    private long ID;
    private Payment payment;
    private OrderType orderType;
    private String name, email, phoneNumber;
    private BillingAddress billingAddress;
    private ShippingAddress shippingAddress;
    private Map<Product, Integer> shoppingCart;

    public Order(OrderType orderType) {
        this.orderType = orderType;
    }

    public Order(String name, String email, String phoneNumber, BillingAddress billingAddress, ShippingAddress shippingAddress) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        User.getInstance().setNewOrder(this);
        orderType = OrderType.CHECKEDOUT;
        ID = System.currentTimeMillis();
        shoppingCart = User.getInstance().getShoppingCart();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getID() {
        return ID;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public Map<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public float cartSumPrice() {
        float sum = 0;
        for(Product p: shoppingCart.keySet())
            sum += p.getFloatPrice() * shoppingCart.get(p);
        return sum;
    }

}
