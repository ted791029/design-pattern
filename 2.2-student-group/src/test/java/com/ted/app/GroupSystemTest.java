package com.ted.app;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupSystemTest {

    private List<Student> students;

    private GroupSystem system;

    @BeforeEach
    void setUp() {
        students = this.studentInit();
    }

    @AfterEach
    void tearDown() {
        students = null;
    }

    @Test
    void givenSystemHasSomeStudent_WhenSystemGroupByLanguage_ThenShouldSuccess(){
        this.system = givenSystemHasSomeStudent();
        List<Group> groups = whenSystemGroupByStrategy(new LanguageBasedGroupingStrategy());
        thenShouldSuccessWithLanguage(groups);
    }

    @Test
    void givenSystemHasSomeStudent_WhenSystemGroupByJobTitle_ThenShouldSuccess(){
        this.system = givenSystemHasSomeStudent();
        List<Group> groups = whenSystemGroupByStrategy(new JobTitleBasedGroupingStrategy());
        thenShouldSuccessWithJobTitle(groups);
    }

    private List<Student> studentInit(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("student1", 9, "C#", "學生"));
        students.add(new Student("student2", 6, "C#", "軟體工程師"));
        students.add(new Student("student3", 3, "C#", "軟體工程師"));
        students.add(new Student("student4", 7, "PHP", "學生"));
        students.add(new Student("student5", 9, "Python", "PM"));
        students.add(new Student("student6", 9, "Java", "PM"));
        students.add(new Student("student7", 1, "C#", "學生"));
        students.add(new Student("student8", 4, "Python", "軟體工程師"));
        students.add(new Student("student9", 5, "C#", "軟體工程師"));
        students.add(new Student("student10", 9, "C#", "軟體工程師"));
        students.add(new Student("student11", 8, "Python", "軟體工程師"));
        students.add(new Student("student12", 8, "C#", "PM"));
        students.add(new Student("student13", 8, "Java", "運維工程師"));
        students.add(new Student("student14", 7, "Python", "學生"));
        students.add(new Student("student15", 4, "Python", "軟體工程師"));
        students.add(new Student("student16", 5, "C#", "PM"));
        students.add(new Student("student17", 4, "Python", "PM"));
        students.add(new Student("student18", 4, "PHP", "學生"));
        students.add(new Student("student19", 10, "Java", "學生"));
        students.add(new Student("student20", 3, "PHP", "軟體工程師"));
        students.add(new Student("student21", 9, "Python", "運維工程師"));
        students.add(new Student("student22", 5, "Java", "軟體工程師"));
        students.add(new Student("student23", 6, "Python", "PM"));
        students.add(new Student("student24", 2, "C#", "軟體工程師"));
        students.add(new Student("student25", 2, "Java", "運維工程師"));
        students.add(new Student("student26", 8, "Python", "運維工程師"));
        students.add(new Student("student27", 7, "Python", "運維工程師"));
        students.add(new Student("student28", 4, "Python", "學生"));
        students.add(new Student("student29", 1, "C#", "學生"));
        students.add(new Student("student30", 1, "Java", "軟體工程師"));
        students.add(new Student("student31", 9, "Python", "軟體工程師"));
        students.add(new Student("student32", 4, "Java", "學生"));
        students.add(new Student("student33", 7, "Java", "運維工程師"));
        students.add(new Student("student34", 9, "C#", "運維工程師"));
        students.add(new Student("student35", 8, "Python", "學生"));
        students.add(new Student("student36", 10, "Java", "學生"));
        students.add(new Student("student37", 8, "Java", "運維工程師"));
        students.add(new Student("student38", 3, "Python", "軟體工程師"));
        students.add(new Student("student39", 7, "C#", "學生"));
        students.add(new Student("student40", 7, "C#", "軟體工程師"));
        students.add(new Student("student41", 9, "PHP", "PM"));
        students.add(new Student("student42", 4, "PHP", "學生"));
        students.add(new Student("student43", 7, "C#", "運維工程師"));
        students.add(new Student("student44", 8, "C#", "軟體工程師"));
        students.add(new Student("student45", 5, "Java", "運維工程師"));
        students.add(new Student("student46", 3, "Python", "軟體工程師"));
        students.add(new Student("student47", 10, "Python", "運維工程師"));
        students.add(new Student("student48", 2, "C#", "運維工程師"));
        students.add(new Student("student49", 1, "Java", "軟體工程師"));
        students.add(new Student("student50", 6, "C#", "PM"));
        students.add(new Student("student51", 5, "Java", "學生"));
        students.add(new Student("student52", 8, "Python", "軟體工程師"));
        students.add(new Student("student53", 6, "Java", "軟體工程師"));
        students.add(new Student("student54", 3, "Python", "運維工程師"));
        students.add(new Student("student55", 1, "PHP", "運維工程師"));
        students.add(new Student("student56", 1, "C#", "軟體工程師"));
        students.add(new Student("student57", 10, "C#", "運維工程師"));
        students.add(new Student("student58", 3, "Java", "PM"));
        students.add(new Student("student59", 2, "C#", "PM"));
        students.add(new Student("student60", 3, "C#", "PM"));
        students.add(new Student("student61", 4, "Java", "運維工程師"));
        students.add(new Student("student62", 5, "PHP", "軟體工程師"));
        students.add(new Student("student63", 7, "Python", "PM"));
        students.add(new Student("student64", 9, "C#", "軟體工程師"));
        students.add(new Student("student65", 9, "Python", "運維工程師"));
        students.add(new Student("student66", 3, "Python", "學生"));
        students.add(new Student("student67", 5, "Java", "學生"));
        students.add(new Student("student68", 1, "Python", "學生"));
        students.add(new Student("student69", 5, "PHP", "運維工程師"));
        students.add(new Student("student70", 6, "C#", "PM"));
        return students;
    }

    private GroupSystem givenSystemHasSomeStudent(){
        return new GroupSystem(this.students);
    }

    private List<Group> whenSystemGroupByStrategy(CutBasedGroupingStrategy strategy){
        this.system.setStrategy(strategy);
        return this.system.group();
    }

    private void thenShouldSuccessWithLanguage(List<Group> groups) {
        for(Group group: groups){
            String language = group.getStudents().get(0).getLanguage();
            for(Student student: group.getStudents()){
                assertEquals(student.getLanguage(), language);
            }
        }
    }

    private void thenShouldSuccessWithJobTitle(List<Group> groups) {
        for(Group group: groups){
            String jobTitle = group.getStudents().get(0).getJobTitle();
            for(Student student: group.getStudents()){
                assertEquals(student.getJobTitle(), jobTitle);
            }
        }
    }
}