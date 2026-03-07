package com.ted.app.factoryProductionScheduling;

import com.ted.app.geneticAlgorithm.Genes;

public class Product extends Genes {

    private String name;

    private int productionTimeHours;

    public Product(String name, int productionTimeHours) {
        this.name = name;
        this.productionTimeHours = productionTimeHours;
    }

    //=======================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductionTimeHours() {
        return productionTimeHours;
    }

    public void setProductionTimeHours(int productionTimeHours) {
        this.productionTimeHours = productionTimeHours;
    }
}
