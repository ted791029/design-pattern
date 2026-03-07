package com.ted.app.shoppingRecommendation;

public class Preference {

    private Customer customer;

    private Type Type;

    private double val;

    public Preference(Customer customer, Type type, double val) {
        this.customer = customer;
        Type = type;
        this.val = val;
    }

    //==============================

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public com.ted.app.shoppingRecommendation.Type getType() {
        return Type;
    }

    public void setType(com.ted.app.shoppingRecommendation.Type type) {
        Type = type;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }
}
