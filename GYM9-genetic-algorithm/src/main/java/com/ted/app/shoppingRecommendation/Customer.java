package com.ted.app.shoppingRecommendation;

import java.util.List;
public class Customer {

    private int budget;
    private ShoppingBag shoppingBag;

    private List<Preference> preferences;

    public Customer(int budget, ShoppingBag shoppingBag) {
        this.budget = budget;
        this.shoppingBag = shoppingBag;
    }

    public int getShoppingBagCapacityKg(){
        return shoppingBag.getCapacityKg();
    }

    //====================
    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public ShoppingBag getShoppingBag() {
        return shoppingBag;
    }

    public void setShoppingBag(ShoppingBag shoppingBag) {
        this.shoppingBag = shoppingBag;
    }

    public List<Preference> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Preference> preferences) {
        this.preferences = preferences;
    }
}
