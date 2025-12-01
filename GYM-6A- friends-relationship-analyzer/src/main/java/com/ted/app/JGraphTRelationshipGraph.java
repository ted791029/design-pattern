package com.ted.app;

import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;

public class JGraphTRelationshipGraph implements RelationshipGraph{

    private ConnectivityInspector<String, DefaultEdge> inspector;

    public JGraphTRelationshipGraph(ConnectivityInspector<String, DefaultEdge> inspector) {
        this.inspector = inspector;
    }

    @Override
    public boolean hasConnection(String name1, String name2) {
        return inspector.pathExists(name1, name2);
    }
}
