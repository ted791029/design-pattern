package com.ted.app;

public class Main {

    public static void main(String[] args){
        String script = "A: B C D\nB: A C D E";
        RelationshipGraphAnalyzer graphAdapter = new JGraphTRelationshipGraphAnalyzerAdapter();
        RelationshipAnalyzer analyzer = new SuperRelationshipAnalyzerAdapter(graphAdapter);
        CLI cli = new CLI(analyzer, script);
        cli.start();
    }
}
