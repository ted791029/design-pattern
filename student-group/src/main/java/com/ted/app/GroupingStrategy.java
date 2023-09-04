package com.ted.app;

import java.util.List;

public interface GroupingStrategy {
    public abstract  List<Group> group(List<Student> students);
}
