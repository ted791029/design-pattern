package com.ted.app;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Arrays;

public class JGraphTRelationshipGraphAnalyzerAdapter implements RelationshipGraphAnalyzer {

    @Override
    public RelationshipGraph parse(String script) {
        //======================
        //A: B C D
        //B: A D E

        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        String[] lines = script.split("\n");
        Arrays.stream(lines)
                .forEach(line -> expandLine(line, graph));
        ConnectivityInspector<String, DefaultEdge> inspector = new ConnectivityInspector<>(graph);
        return new JGraphTRelationshipGraph(inspector);
    }

    private void expandLine(String line, Graph<String, DefaultEdge> graph) {
        String[] parts = line.split(":");
        String mainName = parts[0].trim();
        String[] names = parts[1].trim().split(" ");

        graph.addVertex(mainName);

        Arrays.stream(names)
                .map(String::trim)
                .filter(name -> !name.isEmpty() && mainName.compareTo(name) < 0)
                .forEach(name -> {
                    graph.addVertex(name);
                    graph.addEdge(mainName, name);
                });
    }


}
