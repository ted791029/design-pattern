package com.ted.app;

import java.util.List;

public class GroupSystem {
    List<Student> students;

    LanguageBasedGroupingStrategy strategy;

    public GroupSystem(List<Student> students) {
        this.setStudents(students);
    }

    public GroupSystem(List<Student> students, LanguageBasedGroupingStrategy strategy) {
        this.setStudents(students);
        this.setStrategy(strategy);
    }

    public List<Group> group() {
        return this.strategy.group(this.students);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public LanguageBasedGroupingStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(LanguageBasedGroupingStrategy strategy) {
        this.strategy = strategy;
    }
}
