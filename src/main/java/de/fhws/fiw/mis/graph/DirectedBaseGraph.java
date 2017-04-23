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
        return null;
    }

    @Override
    public boolean containsEdge(Vertex sourceVertex, Vertex targetVertex) {
        return false;
    }

    @Override
    public int getInDegreeOf(Vertex v) {
        return 0;
    }

    @Override
    public Set<Edge> getIncomingEdgesOf(Vertex v) {
        return null;
    }

    @Override
    public int getOutDegreeOf(Vertex v) {
        return 0;
    }

    @Override
    public Set<Edge> getOutgoingEdgesOf(Vertex v) {
        return null;
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
        return isConnected() && getVertexSet().stream()
                .allMatch(x -> getInDegreeOf(x) == getOutDegreeOf(x));
    }
    public boolean hasEulerianPath() {
        return isConnected() && getVertexSet().stream().
                allMatch(v -> Math.abs(getInDegreeOf(v) - getOutDegreeOf(v)) < 2);
    }
    public boolean hasCycle() { //Kahn's Algorithm
        DirectedBaseGraph clone = (DirectedBaseGraph)clone();
        List<Vertex> sortedVertices = new LinkedList<>();
        Queue<Vertex> verticesWOIncEdge = new LinkedList<>(clone.getVertexSet().stream()
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

        return clone.getEdgeSet().size() > 0;
    }

    @Override
    protected Object clone() {
        throw new RuntimeException();
//        return super.clone();
    }
}
