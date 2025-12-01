package com.ted.app;

import java.util.List;

public interface RelationshipAnalyzer {
    public List<String> getMutualFriends(String name1, String name2);
    public RelationshipGraph parse(String script);
}
