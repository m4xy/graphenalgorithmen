package de.fhws.fiw.mis.graph;

import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * Created by maxarndt on 18.04.17.
 */
public class Edge extends DefaultWeightedEdge {


    public int getEdgeWeight() {
        return new Double(super.getWeight()).intValue();
    }
}
