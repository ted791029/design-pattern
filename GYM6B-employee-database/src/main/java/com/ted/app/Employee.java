package com.ted.app;

import java.util.List;

public abstract class Employee {

    abstract public int getAge();

    abstract public void setAge(int age);

    abstract public int getId();

    abstract public void setId(int id);

    abstract public String getName();

    abstract public void setName(String name);

    abstract public List<Employee> getSubordinates();

    abstract public void setSubordinates(List<Employee> subordinates);

    @Override
    public String toString(){
        return "ID: " + getId() + " Age: " + getAge() + " Name: " + getName();
    }
}
