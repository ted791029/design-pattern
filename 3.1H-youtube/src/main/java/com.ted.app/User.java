package com.ted.app;

public class User {
    private String name;

    public User(String name) {
        setName(name);
    }

    /**
     * getter & setter
     **/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
