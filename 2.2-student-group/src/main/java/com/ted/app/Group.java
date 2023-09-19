package com.ted.app;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private int number;
    private List<Student> students;

    public Group(int number) {
        this.setNumber(number);
        this.setStudents(new ArrayList<>());
    }

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

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public List<Group> splitBySize(int size) {
        List<Group> groups = new ArrayList<>();
        int number = 0;
        Group group = new Group(number++);
        for(Student student : students){
            if(group.size() == size){
                groups.add(group);
                group = new Group(number++);
            }
            group.addStudent(student);
        }
        if(group.size() > 0) groups.add(group);
        return groups;
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
