package com.ted.app;

public class Student {
    String name;
    int experience;
    String language;
    String jobTitle;

    public Student(String name, int experience, String language, String jobTitle) {
        this.setName(name);
        this.setExperience(experience);
        this.setLanguage(language);
        this.setJobTitle(jobTitle);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        if(experience < 0) throw  new RuntimeException("experience is more than 0");
        this.experience = experience;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
