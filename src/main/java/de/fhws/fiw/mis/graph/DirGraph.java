package de.fhws.fiw.mis.graph;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.specifics.Specifics;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * Created by maxarndt on 05.04.17.
 */
public class DirGraph extends AbstractGraph implements DirectedGraph<Vertex, DefaultWeightedEdge>, Euler {
    public DirGraph() {
        super(new ClassBasedEdgeFactory<>(DefaultWeightedEdge.class), false, false);
    }

    @Override
    protected Specifics<Vertex, DefaultWeightedEdge> createSpecifics() {
        return super.createDirectedSpecifics();
    }

    @Override
    public Collection<Vertex> getNeighbors(Vertex vertex) {
        Collection<Vertex> vertices = new ArrayList<>();
        Set<DefaultWeightedEdge> edges = edgesOf(vertex);
        for(DefaultWeightedEdge edge : edges) {
            Vertex neighbor = getEdgeTarget(edge);
            if(!neighbor.equals(vertex)) vertices.add(neighbor);
        }
        return vertices;
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
