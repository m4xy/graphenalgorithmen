package de.fhws.fiw.mis.graph;

import com.google.common.collect.ArrayListMultimap;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by maxarndt on 05.04.17.
 */
public class UndirectedBaseGraph extends AbstractGraph {
    public UndirectedBaseGraph() {

    }
    public UndirectedBaseGraph(UndirectedBaseGraph clone) {
        clone.getAllVertices().stream().forEach(v -> addVertex(new VertexBase(v)));
        clone.getAllEdges().stream().forEach(e -> addEdge(e.getSource().getName(), e.getTarget().getName(), e.getWeight()));
    }

    @Override
    public Set<Edge> getEdges(Vertex sourceVertex, Vertex targetVertex) {
        return new HashSet<>(super.edgeMap.get(sourceVertex).stream()
                .filter(e -> (e.getTarget().equals(targetVertex) && e.getSource().equals(sourceVertex) ||
                             (e.getTarget().equals(sourceVertex) && e.getSource().equals(targetVertex))))
                .collect(Collectors.toList()));
    }

    @Override
    public boolean containsEdge(Vertex sourceVertex, Vertex targetVertex) {
        return super.edgeMap.get(sourceVertex).stream()
                .filter(e -> (e.getTarget().equals(targetVertex) && e.getSource().equals(sourceVertex) ||
                        (e.getTarget().equals(sourceVertex) && e.getSource().equals(targetVertex))))
                .count() > 0;
    }

    @Override
    public Collection<Vertex> getNeighbors(Vertex vertex) {
        Collection<Vertex> vertices = new ArrayList<>();
        Set<Edge> edges = getEdgesOf(vertex);
        for(Edge edge : edges) {
            Vertex neighbor = edge.getSource();
            if(neighbor.equals(vertex)) neighbor = edge.getTarget();
            if(!neighbor.equals(vertex)) vertices.add(neighbor);
        }
        return vertices;
    }

    @Override
    public boolean hasEulerianCircuit() {
        return isConnected() && getAllVertices().stream()
                .allMatch(x -> getEdgesOf(x).size() % 2 == 0);
    }

    @Override
    public boolean hasEulerianPath() {
        return isConnected() && getAllVertices().stream()
                .filter(x -> getEdgesOf(x).size() % 2 == 1).count() == 2;
    }

    @Override
    public boolean hasCycle() {
        return getAllEdges().size() >= getAllVertices().size();
    }

    @Override
    public int getMaxFlow(Vertex source, Vertex sink) {
        throw new NotImplementedException();
    }
}
