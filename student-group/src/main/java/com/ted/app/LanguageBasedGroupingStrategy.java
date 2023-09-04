package com.ted.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanguageBasedGroupingStrategy extends CutBasedGroupingStrategy{
    @Override
    protected Object cutBy(Student student) {
        return student.getLanguage();
    }
    @Override
    protected boolean meetMergeCriteria(Group noFullGroup, Group fullGroup){
        return noFullGroup.getStudents().get(0).getLanguage().equals(fullGroup.getStudents().get(0).getLanguage());
    }
}
