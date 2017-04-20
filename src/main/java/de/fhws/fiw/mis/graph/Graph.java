package de.fhws.fiw.mis.graph;

import java.util.Collection;
import java.util.Set;

/**
 * Created by maxarndt on 21.04.17.
 */
public interface Graph {
    Set<EdgeBase> getAllEdges(VertexBase sourceVertex, VertexBase targetVertex);
    EdgeBase getEdge(VertexBase sourceVertex, VertexBase targetVertex);
    EdgeBase addEdge(VertexBase sourceVertex, VertexBase targetVertex);
    boolean addVertex(VertexBase v);
    boolean containsEdge(VertexBase sourceVertex, VertexBase targetVertex);
    boolean containsEdge(EdgeBase e);
    boolean containsVertex(VertexBase v);
    Set<EdgeBase> getEdgeSet();
    Set<EdgeBase> getEdgesOf(VertexBase v);
    EdgeBase removeEdge(VertexBase sourceVertex, VertexBase targetVertex);
    boolean removeEdge(EdgeBase e);
    boolean removeVertex(VertexBase v);
    Set<VertexBase> getVertexSet();
    VertexBase getEdgeSource(EdgeBase e);
    VertexBase getEdgeTarget(EdgeBase e);
    int getEdgeWeight(EdgeBase e);
    void setEdgeWeight(Edge e, int weight);
    Collection<VertexBase> getNeighbors(VertexBase vertex);
    boolean hasCycle();
    boolean hasEulerianCircuit();
    boolean hasEulerianPath();
    boolean isConnected();
    boolean hasWeightedEdges();
    Collection<VertexBase> breadthFirstSearch(VertexBase startVertex);
    Collection<VertexBase> depthFirstSearch(VertexBase startVertex);
}
