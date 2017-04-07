package de.fhws.fiw.mis.graph;

import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.specifics.Specifics;

/**
 * Created by maxarndt on 05.04.17.
 */
public class UndirGraph extends GraphImpl {
    public UndirGraph() {
        super(new ClassBasedEdgeFactory<>(DefaultWeightedEdge.class), true, true);
    }

    @Override
    protected Specifics<Vertex, DefaultWeightedEdge> createSpecifics() {
        return super.createUndirectedSpecifics();
    }

    public Boolean hasEulerianCircuit() {
        return isConnected() && vertexSet().stream()
                .allMatch(x -> edgesOf(x).size() % 2 == 0);
    }
    public Boolean hasEulerianPath() {
        return isConnected() && vertexSet().stream()
                .filter(x -> edgesOf(x).size() % 2 == 1).count() == 2;
    }
}
