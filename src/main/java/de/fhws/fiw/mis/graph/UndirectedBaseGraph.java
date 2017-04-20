package de.fhws.fiw.mis.graph;

import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.specifics.Specifics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Created by maxarndt on 05.04.17.
 */
public class UndirectedBaseGraph extends AbstractGraph {
    public UndirectedBaseGraph() {
        super(false, false);
    }

    @Override
    public Collection<VertexBase> getNeighbors(VertexBase vertex) {
        Collection<VertexBase> vertices = new ArrayList<>();
        Set<EdgeBase> edges = getEdgesOf(vertex);
        for(EdgeBase edge : edges) {
            VertexBase neighbor = getEdgeSource(edge);
            if(neighbor.equals(vertex)) neighbor = getEdgeTarget(edge);
            if(!neighbor.equals(vertex)) vertices.add(neighbor);
        }
        return vertices;
    }

    public boolean hasEulerianCircuit() {
        return isConnected() && getVertexSet().stream()
                .allMatch(x -> getEdgesOf(x).size() % 2 == 0);
    }
    public boolean hasEulerianPath() {
        return isConnected() && getVertexSet().stream()
                .filter(x -> getEdgesOf(x).size() % 2 == 1).count() == 2;
    }
    public boolean hasCycle() {
        return getEdgeSet().size() >= getVertexSet().size();
    }
}
