package com.ted.app.factoryProductionScheduling;

import java.util.List;
import java.util.Map;

public class Factory {

    private List<Machine> machines;

    private List<Worker> workers;

    private List<Product> products;

    private Map<String, Integer> requiredQuantities;

    public Factory(List<Machine> machines,
                   List<Worker> workers,
                   List<Product> products,
                   Map<String, Integer> requiredQuantities) {
        this.machines = machines;
        this.workers = workers;
        this.products = products;
        this.requiredQuantities = requiredQuantities;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getRequiredQuantity(String productName) {
        return requiredQuantities.getOrDefault(productName, 0);
    }

    public int getMachineCount() {
        return machines.size();
    }

    public int getWorkerCount() {
        return workers.size();
    }
}
