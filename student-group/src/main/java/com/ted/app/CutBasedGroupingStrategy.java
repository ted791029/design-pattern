package com.ted.app;

import java.util.*;

public abstract  class CutBasedGroupingStrategy implements GroupingStrategy{
    int GROUP_MIN_SIZE = 6;
    public  List<Group> group(List<Student> students){
        //先用程式語言切一刀
        Map<Object, Group> fristCut = new HashMap<>();
        int number = 0;

        for (Student student : students) {
            Object key = this.cutBy(student);
            if(!fristCut.containsKey(key)){
                fristCut.put(key, new Group(number++));
            }
            fristCut.get(key).addStudent(student);
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
            for(Group fullGoup : fullGroups){
                if(meetMergeCriteria(noFullGroup, fullGoup)){
                    fullGoup.merge(noFullGroup);
                    break;
                }
            }
        }
        return fullGroups;
    }

    protected abstract  Object cutBy(Student student);

    protected boolean meetMergeCriteria(Group noFullGroup, Group fullGroup){
        return true;
    }
}
