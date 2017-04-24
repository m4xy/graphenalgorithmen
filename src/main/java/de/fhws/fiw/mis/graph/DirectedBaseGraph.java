package de.fhws.fiw.mis.graph;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by maxarndt on 05.04.17.
 */
public class DirectedBaseGraph extends AbstractGraph implements DirectedGraph, Cloneable {

    public DirectedBaseGraph() {

    }

    @Override
    public Set<Edge> getEdges(Vertex sourceVertex, Vertex targetVertex) {
        return new HashSet<>(super.edgeMap.get(sourceVertex).stream()
                .filter(e -> e.getTarget().equals(targetVertex) && e.getSource().equals(sourceVertex))
                .collect(Collectors.toList()));
    }

    @Override
    public boolean containsEdge(Vertex sourceVertex, Vertex targetVertex) {
        return super.edgeMap.get(sourceVertex).stream()
                .filter(e -> e.getTarget().equals(targetVertex) && e.getSource().equals(sourceVertex))
                .count() > 0;
    }

    @Override
    public int getInDegreeOf(Vertex v) {
        return (int)super.edgeMap.get(v).stream().filter(e -> e.getTarget().equals(v)).count();
    }

    @Override
    public Set<Edge> getIncomingEdgesOf(Vertex v) {
        return new HashSet<>(super.edgeMap.get(v).stream().filter(e -> e.getTarget().equals(v)).collect(Collectors.toList()));
    }

    @Override
    public int getOutDegreeOf(Vertex v) {
        return (int)super.edgeMap.get(v).stream().filter(e -> e.getSource().equals(v)).count();
    }

    @Override
    public Set<Edge> getOutgoingEdgesOf(Vertex v) {
        return new HashSet<>(super.edgeMap.get(v).stream().filter(e -> e.getSource().equals(v)).collect(Collectors.toList()));
    }

    @Override
    public Collection<Vertex> getNeighbors(Vertex vertex) {
        Collection<Vertex> vertices = new ArrayList<>();
        Set<Edge> edges = getEdgesOf(vertex);
        for(Edge edge : edges) {
            Vertex neighbor = edge.getTarget();
            if(!neighbor.equals(vertex)) vertices.add(neighbor);
        }
        return vertices;
    }

    public boolean hasEulerianCircuit() {
        return isConnected() && getAllVertices().stream()
                .allMatch(x -> getInDegreeOf(x) == getOutDegreeOf(x));
    }
    public boolean hasEulerianPath() {
        return isConnected() && getAllVertices().stream().
                allMatch(v -> Math.abs(getInDegreeOf(v) - getOutDegreeOf(v)) < 2);
    }
    public boolean hasCycle() { //Kahn's Algorithm
        DirectedBaseGraph clone = (DirectedBaseGraph)clone();
        List<Vertex> sortedVertices = new LinkedList<>();
        Queue<Vertex> verticesWOIncEdge = new LinkedList<>(clone.getAllVertices().stream()
                .filter(v -> clone.getInDegreeOf(v) == 0)
                .collect(Collectors.toList()));
        while(!verticesWOIncEdge.isEmpty()) {
            Vertex curVertex = verticesWOIncEdge.remove();
            sortedVertices.add(curVertex);
            clone.getEdgesOf(curVertex).stream().forEach(e -> {
                Vertex targetVertex = e.getTarget();
                clone.removeEdge(e);
                if(clone.getInDegreeOf(targetVertex) == 0)
                    verticesWOIncEdge.add(targetVertex);
            });
        }

        return clone.getAllEdges().size() > 0;
    }

    @Override
    protected Object clone() {
        throw new RuntimeException();
//        return super.clone();
    }
}
