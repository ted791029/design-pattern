package com.ted.app.lib;

import com.ted.app.lib.util.ValidUtil;

import java.util.List;

public class Patient {

    private int age;


    private List<Case> cases;

    private Gender gender;

    private float height;

    private String id;

    private String name;

    private float weight;

    public Patient(int age, Gender gender, float height, String id, String name, float weight) {
        setAge(age);
        setGender(gender);
        setHeight(height);
        setId(id);
        setName(name);
        setWeight(weight);
    }

    public void addCase(Case c) {
        cases.add(c);
    }

    public double bmi() {
        double heightM = height / 100.0;
        double bmi = weight / Math.pow(heightM, 2);
        return Math.round(bmi * 100.0) / 100.0;
    }

    public void update(int age, List<Case> cases, Gender gender, float height, String id, String name, float weight) {
        setAge(age);
        this.cases = cases;
        setGender(gender);
        setHeight(height);
        setId(id);
        setName(name);
        setWeight(weight);
    }


    //=======================================

    public int getAge() {
        return age;
    }

    public void setAge(int age) {

        if (!ValidUtil.isInRange(1, 180, age)) {
            System.out.println("年齡為1-180，現值: " + age);
        }

        this.age = age;
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {

        if (!ValidUtil.isInRange(1, 500, height)) {
            System.out.println("身高為1-500，現值: " + height);
        }

        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {

        if (!ValidUtil.isValidTaiwanId(id)) {
            System.out.println(id + "不合法");
        }

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        if (!ValidUtil.isInRange(1, 30, name.length())) {
            System.out.println("姓名長度為1-30");
        }

        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {

        if (!ValidUtil.isInRange(1, 500, weight)) {
            System.out.println("體重為1-500，現值: " + weight);
        }

        this.weight = weight;
    }
}
