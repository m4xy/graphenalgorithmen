package de.fhws.fiw.mis.graph;


import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by maxarndt on 05.04.17.
 */
public class DirectedBaseGraph extends AbstractGraph implements DirectedGraph, Cloneable {

    public DirectedBaseGraph() {
        super(false, false);
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
    public Collection<VertexBase> getNeighbors(VertexBase vertex) {
        Collection<VertexBase> vertices = new ArrayList<>();
        Set<EdgeBase> edges = getEdgesOf(vertex);
        for(EdgeBase edge : edges) {
            VertexBase neighbor = getEdgeTarget(edge);
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
        List<VertexBase> sortedVertices = new LinkedList<>();
        Queue<VertexBase> verticesWOIncEdge = new LinkedList<>(clone.getVertexSet().stream()
                .filter(v -> clone.getInDegreeOf(v) == 0)
                .collect(Collectors.toList()));
        while(!verticesWOIncEdge.isEmpty()) {
            VertexBase curVertex = verticesWOIncEdge.remove();
            sortedVertices.add(curVertex);
            clone.getEdgesOf(curVertex).stream().forEach(e -> {
                VertexBase targetVertex = clone.getEdgeTarget(e);
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
