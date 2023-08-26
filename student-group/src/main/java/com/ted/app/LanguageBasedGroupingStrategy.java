package com.ted.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanguageBasedGroupingStrategy {

    int GROUP_MIN_SIZE = 6;

    public List<Group> group(List<Student> students) {
        //先用程式語言切一刀
        Map<String, Group> fristCut = new HashMap<>();
        int number = 0;

        for (Student student : students) {
            if(!fristCut.containsKey(student.getLanguage())){
                fristCut.put(student.getLanguage(), new Group(number++));
            }
            fristCut.get(student.getLanguage()).addStudent(student);
        }
        List<Group> fristCutGroups = new ArrayList<>(fristCut.values());
        //再用人數切第二刀
        List<Group> secondCutGroups = new ArrayList<>();

        for(Group group : fristCutGroups){
            secondCutGroups.addAll(group.splitBySize(GROUP_MIN_SIZE));
        }
        //將人數不足，合併至相同語言的組別
        List<Group> fullGroups = new ArrayList<>();
        List<Group> noFullGroups = new ArrayList<>();

        for(Group group :  secondCutGroups){
            if(group.size() >= GROUP_MIN_SIZE){
                fullGroups.add(group);
            }else{
                noFullGroups.add(group);
            }
        }

        for(Group noFullGroup : noFullGroups){
            boolean isAdd = false;
            for(Group fullGoup : fullGroups){
                if(!isAdd && noFullGroup.getStudents().get(0).getLanguage().equals(fullGoup.getStudents().get(0).getLanguage())){
                    fullGoup.merge(noFullGroup);
                    isAdd = true;
                }
            }
        }
        return fullGroups;
    }
}
