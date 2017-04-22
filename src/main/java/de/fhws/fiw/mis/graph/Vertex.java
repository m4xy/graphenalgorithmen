package de.fhws.fiw.mis.graph;

/**
 * Created by maxarndt on 21.04.17.
 */
public interface Vertex {
    String getName();
    int getData();
    boolean isInEdge(Edge e);
    boolean isOutEdge(Edge e);
}
