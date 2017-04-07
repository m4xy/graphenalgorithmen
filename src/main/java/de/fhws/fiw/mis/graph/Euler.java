package de.fhws.fiw.mis.graph;

import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * Created by maxarndt on 03.04.17.
 */
public class Euler {

    public static Boolean hasEulerianCircuit(UndirGraph graph) {
        return graph.isConnected() && graph.vertexSet().stream()
                .allMatch(x -> graph.edgesOf(x).size() % 2 == 0);
    }
    public static Boolean hasEulerianCircuit(DirGraph graph) {
        return graph.isConnected() && graph.vertexSet().stream()
                .allMatch(x -> graph.inDegreeOf(x) == graph.outDegreeOf(x));
    }

    public static Boolean hasEulerianPath(UndirGraph graph) {
        return graph.isConnected() && graph.vertexSet().stream()
                .filter(x -> graph.edgesOf(x).size() % 2 == 1).count() == 2;
    }
    public static Boolean hasEulerianPath(DirGraph graph) {
        return false;
    }
}
