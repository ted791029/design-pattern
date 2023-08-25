package com.ted.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanguageBasedGroupingStrategy {

    public List<Group> group(List<Student> students) {
        //先用程式語言切一刀
        Map<String, List<Student>> map = new HashMap<>();
        List<Student> studentsTemp = null;
        for (Student student : students) {
            if(!map.containsKey(student.language)){
                studentsTemp = new ArrayList<>();
                studentsTemp.add(student);
            }else{
                studentsTemp = map.get(student.language);
                studentsTemp.add(student);
            }
            map.put(student.language, studentsTemp);
        }
        //再用人數切第二刀
        List<Group> fullGroups = new ArrayList<>();
        List<Group> noFullGroups = new ArrayList<>();
        int number = 0;
        for(String key : map.keySet()){
            List<Student> temp = new ArrayList<>();
            for(Student student : map.get(key)){
                if(temp.size() == 6) {
                    fullGroups.add(new Group(number++, temp));
                    temp = new ArrayList<>();
                }
                temp.add(student);
            }
            if(temp.size() > 0 && temp.size() < 6) noFullGroups.add(new Group(number++, temp));
        }
        //將人數不足，合併至相同語言的組別
        for(Group noFullGroup : noFullGroups){
            boolean isAdd = false;
            for(Group fullGoup : fullGroups){
                if(!isAdd && noFullGroup.getStudents().get(0).language.equals(fullGoup.getStudents().get(0).language)){
                    fullGoup.merge(noFullGroup);
                    isAdd = true;
                }
            }
        }
        return fullGroups;
    }
}
