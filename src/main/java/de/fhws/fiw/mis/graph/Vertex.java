package de.fhws.fiw.mis.graph;

import de.fhws.fiw.mis.graph.color.HtmlColor;
import de.fhws.fiw.mis.graph.color.VertexColor;

/**
 * Created by maxarndt on 21.04.17.
 */
public interface Vertex {
    String getName();
    int getData();
    VertexColor getColor();
    HtmlColor getHtmlColor();
    void setColor(HtmlColor color);
    boolean isInEdge(Edge e);
    boolean isOutEdge(Edge e);
}
