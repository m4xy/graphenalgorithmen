package de.fhws.fiw.mis.graph;

import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.specifics.Specifics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Created by maxarndt on 05.04.17.
 */
public class UndirGraph extends AbstractGraph implements Euler {
    public UndirGraph() {
        super(new ClassBasedEdgeFactory<>(DefaultWeightedEdge.class), false, false);
    }

    @Override
    protected Specifics<Vertex, DefaultWeightedEdge> createSpecifics() {
        return super.createUndirectedSpecifics();
    }

    @Override
    public Collection<Vertex> getNeighbors(Vertex vertex) {
        Collection<Vertex> vertices = new ArrayList<>();
        Set<DefaultWeightedEdge> edges = edgesOf(vertex);
        for(DefaultWeightedEdge edge : edges) {
            Vertex neighbor = getEdgeSource(edge);
            if(neighbor.equals(vertex)) neighbor = getEdgeTarget(edge);
            if(!neighbor.equals(vertex)) vertices.add(neighbor);
        }
        return vertices;
    }

    public boolean hasEulerianCircuit() {
        return isConnected() && vertexSet().stream()
                .allMatch(x -> edgesOf(x).size() % 2 == 0);
    }
    public boolean hasEulerianPath() {
        return isConnected() && vertexSet().stream()
                .filter(x -> edgesOf(x).size() % 2 == 1).count() == 2;
    }
    public boolean hasCycle() {
        return edgeSet().size() >= vertexSet().size();
    }
}
