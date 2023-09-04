package com.ted.app;

import java.util.List;

public class GroupSystem {
    List<Student> students;

    CutBasedGroupingStrategy strategy;

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

    public CutBasedGroupingStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(CutBasedGroupingStrategy strategy) {
        this.strategy = strategy;
    }
}
