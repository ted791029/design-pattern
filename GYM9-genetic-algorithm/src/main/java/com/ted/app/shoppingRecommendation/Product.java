package com.ted.app.shoppingRecommendation;

import com.ted.app.geneticAlgorithm.Genes;
public class Product extends Genes {

    private int id;
    private int price;
    private int weightKg;
    private Type type;

    public Product(int id, int price, int weightKg, Type type) {
        this.id = id;
        this.price = price;
        this.weightKg = weightKg;
        this.type = type;
    }


    //======================================
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(int weightKg) {
        this.weightKg = weightKg;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
