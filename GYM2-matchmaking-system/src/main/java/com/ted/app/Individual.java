package com.ted.app;

import java.util.HashMap;
import java.util.Map;

public class Individual {
    private int id;
    private Gender gender;
    private int age;
    private String intro;
    private String habits;

    private Coord coord;

    public Individual(int id, Gender gender, int age, String intro, String habits, Coord coord) {
        this.setId(id);
        this.setGender(gender);
        this.setAge(age);
        this.setIntro(intro);
        this.setHabits(habits);
        this.setCoord(coord);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) throw new RuntimeException("id: 不可小於0");
        this.id = id;
    }

    public int getRepeatedHabitsCounts(Individual target){
        String[] targetHabitArr = target.habits.split(",");
        String[] habitArr = this.habits.split(",");
        Map<String, Integer> map = new HashMap<>();
        for(String habit : habitArr){
            map.put(habit, map.getOrDefault(habit, 0) + 1);
        }
        int count = 0;
        for(String habit : targetHabitArr){
            if(map.containsKey(habit)) count++;
        }
        return count;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        if (id < 18) throw new RuntimeException("年齡: 不可小於18");
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getHabits() {
        return habits;
    }

    public void setHabits(String habits) {
        this.habits = habits;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }
}
