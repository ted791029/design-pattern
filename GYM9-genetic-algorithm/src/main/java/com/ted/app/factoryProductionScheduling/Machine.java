package com.ted.app.factoryProductionScheduling;

public class Machine {

    private String id;

    public Machine(String id) {
        this.id = id;
    }

    //============================

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
