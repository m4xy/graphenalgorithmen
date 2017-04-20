package de.fhws.fiw.mis.graph;

import java.util.Set;

/**
 * Created by maxarndt on 21.04.17.
 */
public interface DirectedGraph {
    int getInDegreeOf(Vertex v);
    Set<Edge> getIncomingEdgesOf(Vertex v);
    int getOutDegreeOf(Vertex v);
    Set<Edge> getOutgoingEdgesOf(Vertex v);
}
