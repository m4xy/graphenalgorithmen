package de.fhws.fiw.mis.graph;

import java.util.Collection;
import java.util.Set;

/**
 * Created by maxarndt on 21.04.17.
 */
public interface Graph {
    Set<Edge> getAllEdges(Vertex sourceVertex, Vertex targetVertex);
    Edge getEdge(Vertex sourceVertex, Vertex targetVertex);
    Edge addEdge(Vertex sourceVertex, Vertex targetVertex);
    Edge addEdge(Vertex sourceVertex, Vertex targetVertex, int weight);
    void addEdge(Edge e);
    void addVertex(Vertex v);
    boolean containsEdge(Vertex sourceVertex, Vertex targetVertex);
    boolean containsEdge(Edge e);
    boolean containsVertex(Vertex v);
    Set<Edge> getEdgeSet();
    Set<Edge> getEdgesOf(Vertex v);
    Edge removeEdge(Vertex sourceVertex, Vertex targetVertex);
    void removeEdge(Edge e);
    void removeVertex(Vertex v);
    Set<Vertex> getVertexSet();
    Vertex getEdgeSource(Edge e);
    Vertex getEdgeTarget(Edge e);
    int getEdgeWeight(Edge e);
    void setEdgeWeight(Edge e, int weight);
    Collection<Vertex> getNeighbors(Vertex vertex);
    boolean hasCycle();
    boolean hasEulerianCircuit();
    boolean hasEulerianPath();
    boolean isConnected();
    boolean hasWeightedEdges();
    Collection<Vertex> breadthFirstSearch(Vertex startVertex);
    Collection<Vertex> depthFirstSearch(Vertex startVertex);
}
