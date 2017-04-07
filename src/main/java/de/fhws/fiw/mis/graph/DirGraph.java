package de.fhws.fiw.mis.graph;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.specifics.Specifics;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by maxarndt on 05.04.17.
 */
public class DirGraph extends GraphImpl implements DirectedGraph<Vertex, DefaultWeightedEdge>, Euler {
    public DirGraph() {
        super(new ClassBasedEdgeFactory<>(DefaultWeightedEdge.class), false, false);
    }

    @Override
    protected Specifics<Vertex, DefaultWeightedEdge> createSpecifics() {
        return super.createDirectedSpecifics();
    }

    public boolean hasEulerianCircuit() {
        return isConnected() && vertexSet().stream()
                .allMatch(x -> inDegreeOf(x) == outDegreeOf(x));
    }
    public boolean hasEulerianPath() {
        return isConnected() && vertexSet().stream().
                allMatch(v -> Math.abs(inDegreeOf(v) - outDegreeOf(v)) < 2);
    }
    public boolean hasCycle() {
        throw new NotImplementedException();
    }
}
