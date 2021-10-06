package com.codecool.shop.model;

public class Payment {

    private String email;
    private String password;
    private String creditCardNumber;
    private String cvs;
    private String expirationDate;
    private String nameOnCard;
    private PaymentType paymentType;

    public Payment(String creditCardNumber, String cvs, String expirationDate, String nameOnCard) {
        this.creditCardNumber = creditCardNumber;
        this.cvs = cvs;
        this.expirationDate = expirationDate;
        this.nameOnCard = nameOnCard;
        paymentType = PaymentType.CREDIT_CARD;
    }

    public Payment(String email, String password) {
        this.email = email;
        this.password = password;
        paymentType = PaymentType.PAYPAL;
    }

    enum PaymentType {
        CREDIT_CARD,
        PAYPAL;
    }


}
