package de.fhws.fiw.mis.graph;

import de.fhws.fiw.mis.graph.color.VertexColor;

/**
 * Created by maxarndt on 21.04.17.
 */
public interface Vertex {
    String getName();
    int getData();
    VertexColor getColor();
    void setColor(VertexColor color);
    boolean isInEdge(Edge e);
    boolean isOutEdge(Edge e);
}
