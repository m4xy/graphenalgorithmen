package de.fhws.fiw.mis.graph;

import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.specifics.Specifics;

/**
 * Created by maxarndt on 05.04.17.
 */
public class UndirGraph extends GraphImpl {
    public UndirGraph() {
        super(new ClassBasedEdgeFactory<>(DefaultWeightedEdge.class), true, true);
    }

    @Override
    protected Specifics<Vertex, DefaultWeightedEdge> createSpecifics() {
        return super.createUndirectedSpecifics();
    }
}
