package de.fhws.fiw.mis.graph;

import java.util.Collection;
import java.util.Set;

/**
 * Created by maxarndt on 21.04.17.
 */
public interface Graph {
    Set<Edge> getEdges(Vertex sourceVertex, Vertex targetVertex);
    Edge addEdge(String sourceVertexName, String targetVertexName);
    Edge addEdge(String sourceVertexName, String targetVertexName, int weight);
    void addEdge(Edge e);
    void addVertex(Vertex v);
    boolean containsEdge(Vertex sourceVertex, Vertex targetVertex);
    boolean containsEdge(Edge e);
    boolean containsVertex(Vertex v);
    boolean containsVertex(String name);
    Set<Edge> getAllEdges();
    Set<Edge> getEdgesOf(Vertex v);
    void removeEdges(Vertex sourceVertex, Vertex targetVertex);
    void removeEdge(Edge e);
    void removeVertex(Vertex v);
    Vertex getVertex(String name);
    Set<Vertex> getAllVertices();
    Collection<Vertex> getNeighbors(Vertex vertex);
    boolean hasCycle();
    boolean hasEulerianCircuit();
    boolean hasEulerianPath();
    boolean isConnected();
    boolean hasWeightedEdges();
    Collection<Vertex> breadthFirstSearch(Vertex startVertex);
    Collection<Vertex> depthFirstSearch(Vertex startVertex);
}
