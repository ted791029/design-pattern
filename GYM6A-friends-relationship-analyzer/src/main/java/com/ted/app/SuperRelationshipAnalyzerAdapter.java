package com.ted.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SuperRelationshipAnalyzerAdapter implements RelationshipAnalyzer {

    private RelationshipGraphAnalyzer graphAnalyzer;
    private final SuperRelationshipAnalyzer analyzer = new SuperRelationshipAnalyzer();

    public SuperRelationshipAnalyzerAdapter(RelationshipGraphAnalyzer relationshipGraphAdapter) {
        this.graphAnalyzer = relationshipGraphAdapter;
    }

    @Override
    public List<String> getMutualFriends(String name1, String name2) {
        List<String> result = new ArrayList<>();
        Map<String, List<String>> friendsMap = analyzer.getFriendsMap();

        if (!friendsMap.containsKey(name1)) {
            return result;
        }

        List<String> friends = friendsMap.get(name1);
        return friends.stream()
                .filter(friendName -> !friendName.equals(name2))
                .filter(friendName -> analyzer.isMutualFriend(friendName, name1, name2))
                .collect(Collectors.toList());
    }

    @Override
    public RelationshipGraph parse(String script) {
        //======================
        //A: B C D
        //B: A D E

        RelationshipGraph graph = graphAnalyzer.parse(script);
        String newScript = toSuperRelationshipAnalyzerFormat(script);
        analyzer.init(newScript);
        return graph;
    }

    private String toSuperRelationshipAnalyzerFormat(String script) {
        String[] lines = script.split("\n");
        return Arrays.stream(lines)
                .flatMap(this::expandLine)
                .collect(Collectors.joining());
    }

    private Stream<String> expandLine(String line) {
        //======================
        //A -- B
        //A -- C
        //A -- D
        //B -- D
        //B -- E

        String[] parts = line.split(":");
        String mainName = parts[0];
        String[] names = parts[1].split(" ");

        return Arrays.stream(names)
                .filter(name -> !name.isEmpty() && name.charAt(0) > mainName.charAt(0))
                .map(name -> mainName + " -- " + name + "\n");
    }
}
