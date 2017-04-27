package de.fhws.fiw.mis.graph;

import de.fhws.fiw.mis.graph.color.EdgeColor;

/**
 * Created by maxarndt on 21.04.17.
 */
public interface Edge {
    Vertex getSource();
    Vertex getTarget();
    int getWeight();
    void setWeight(int weight);
    void incrementWeight(int delta);
    EdgeColor getColor();
    void setColor(EdgeColor color);
}
