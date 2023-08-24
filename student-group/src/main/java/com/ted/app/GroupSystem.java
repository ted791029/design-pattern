package com.ted.app;

import java.util.List;

public class GroupSystem {
    List<Student> students;

    LanguageBasedGroupingStrategy strategy;

    public List<Group> group() {
        return this.strategy.group(this.students);
    }
}
