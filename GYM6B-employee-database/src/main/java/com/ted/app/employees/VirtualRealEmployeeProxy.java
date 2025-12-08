package com.ted.app.employees;

import com.ted.app.Database;
import com.ted.app.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VirtualRealEmployeeProxy extends Employee {

    private Database database;

    private RealEmployee realEmployee;

    private String subordinateIds;


    public VirtualRealEmployeeProxy(int age, Database database, int id, String name, String subordinateIds) {
        realEmployee = new RealEmployee(age, id, name);
        this.database = database;
        this.subordinateIds = subordinateIds;
    }

    @Override
    public int getAge() {
        return realEmployee.getAge();
    }

    @Override
    public void setAge(int age) {
        realEmployee.setAge(age);
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    @Override
    public int getId() {
        return realEmployee.getId();
    }

    @Override
    public void setId(int id) {
        realEmployee.setId(id);
    }

    @Override
    public String getName() {
        return realEmployee.getName();
    }

    @Override
    public void setName(String name) {
        realEmployee.setName(name);
    }

    public RealEmployee getRealEmployee() {
        return realEmployee;
    }

    public void setRealEmployee(RealEmployee realEmployee) {
        this.realEmployee = realEmployee;
    }

    public String getSubordinateIds() {
        return subordinateIds;
    }

    public void setSubordinateIds(String subordinateIds) {
        this.subordinateIds = subordinateIds;
    }

    @Override
    public List<Employee> getSubordinates() {

        if (realEmployee.getSubordinates() == null) {
            realEmployee.setSubordinates(initSubordinates());
        }

        return realEmployee.getSubordinates();
    }

    @Override
    public void setSubordinates(List<Employee> subordinates) {
        realEmployee.setSubordinates(subordinates);
    }

    private List<Employee> initSubordinates() {

        if (subordinateIds == null) {
            return new ArrayList<>();
        }

        String[] subordinateIdArr = subordinateIds.split(",");

        return Arrays.stream(subordinateIdArr)
                .map(Integer::parseInt)
                .map(database::getEmployeeById)
                .collect(Collectors.toList());
    }
}
