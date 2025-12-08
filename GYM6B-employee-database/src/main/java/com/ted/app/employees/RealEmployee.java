package com.ted.app.employees;

import com.ted.app.Employee;

import java.util.List;

public class RealEmployee extends Employee {

    private int age;

    private int id;

    private String name;

    private List<Employee> subordinates;

    public RealEmployee(int age, int id, String name) {
        this.age = age;
        this.id = id;
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Employee> getSubordinates() {
        return subordinates;
    }

    @Override
    public void setSubordinates(List<Employee> subordinates) {
        this.subordinates = subordinates;
    }
}
