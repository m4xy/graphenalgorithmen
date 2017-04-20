package de.fhws.fiw.mis.graph;

/**
 * Created by maxarndt on 21.04.17.
 */
public interface Vertex {
    boolean isInEdge(Edge e);
    boolean isOutEdge(Edge e);
}
