package com.ted.app;

import java.util.List;

public class Group {
    int number;
    List<Student> students;

    public Group(int number, List<Student> students) {
        this.setNumber(number);
        this.setStudents(students);
    }

    public int size() {
        return students.size();
    }

    public void merge(Group group) {
        this.students.addAll(group.students);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
