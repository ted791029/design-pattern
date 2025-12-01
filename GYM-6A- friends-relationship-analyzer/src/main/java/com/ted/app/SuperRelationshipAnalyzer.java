package com.ted.app;

import java.util.*;

public class SuperRelationshipAnalyzer {

    private Map<String, List<String>> friendsMap;

    public SuperRelationshipAnalyzer() {
        friendsMap = new HashMap<>();
    }

    public void init(String script) {
        String[] lines = script.split("\n");

        Arrays.stream(lines)
                .map(line -> line.split(" -- "))
                .map(parts -> new Friend(parts[0], parts[1]))
                .filter(p -> p.name2.charAt(0) >= p.name1.charAt(0))
                .forEach(p -> {
                    insertMap(p.name1, p.name2);
                    insertMap(p.name2, p.name1);
                });
    }

    public boolean isMutualFriend(String targetName, String name1, String name2) {
        if (friendsMap.containsKey(targetName)) {
            List<String> friends = friendsMap.get(targetName);
            return friends.contains(name1) && friends.contains(name2);
        }
        return false;
    }

    private void insertMap(String key, String val) {
        if (friendsMap.containsKey(key)) {
            List<String> friends = friendsMap.get(key);
            friends.add(val);
        } else {
            List<String> friends = new ArrayList<>();
            friends.add(val);
            friendsMap.put(key, friends);
        }
    }

    //=======================


    public Map<String, List<String>> getFriendsMap() {
        return friendsMap;
    }

    public void setFriendsMap(Map<String, List<String>> friendsMap) {
        this.friendsMap = friendsMap;
    }

    private class Friend {
        String name1;
        String name2;

        public Friend(String name1, String name2) {
            this.name1 = name1;
            this.name2 = name2;
        }

        private Map<String, List<String>> friendsMap;
    }
}
