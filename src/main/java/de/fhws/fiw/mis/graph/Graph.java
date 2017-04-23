package de.fhws.fiw.mis.graph;

import java.util.Collection;
import java.util.Set;

/**
 * Created by maxarndt on 21.04.17.
 */
public interface Graph {
    Set<Edge> getEdges(Vertex sourceVertex, Vertex targetVertex);
    Edge addEdge(Vertex sourceVertex, Vertex targetVertex);
    Edge addEdge(Vertex sourceVertex, Vertex targetVertex, int weight);
    void addEdge(Edge e);
    void addVertex(Vertex v);
    boolean containsEdge(Vertex sourceVertex, Vertex targetVertex);
    boolean containsEdge(Edge e);
    boolean containsVertex(Vertex v);
    Set<Edge> getEdgeSet();
    Set<Edge> getEdgesOf(Vertex v);
    void removeEdges(Vertex sourceVertex, Vertex targetVertex);
    void removeEdge(Edge e);
    void removeVertex(Vertex v);
    Set<Vertex> getVertexSet();
    Collection<Vertex> getNeighbors(Vertex vertex);
    boolean hasCycle();
    boolean hasEulerianCircuit();
    boolean hasEulerianPath();
    boolean isConnected();
    boolean hasWeightedEdges();
    Collection<Vertex> breadthFirstSearch(Vertex startVertex);
    Collection<Vertex> depthFirstSearch(Vertex startVertex);
}
