package com.ted.app;

public class JobTitleBasedGroupingStrategy extends CutBasedGroupingStrategy{
    @Override
    protected Object cutBy(Student student) {
        return student.getJobTitle();
    }
    @Override
    protected boolean meetMergeCriteria(Group noFullGroup, Group fullGroup){
        return noFullGroup.getStudents().get(0).getJobTitle().equals(fullGroup.getStudents().get(0).getJobTitle());
    }
}
