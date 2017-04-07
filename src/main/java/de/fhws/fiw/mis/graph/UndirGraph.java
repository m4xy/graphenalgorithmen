package de.fhws.fiw.mis.graph;

import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.graph.specifics.Specifics;

/**
 * Created by maxarndt on 05.04.17.
 */
public class UndirGraph<V, E> extends GraphImpl<V, E> {
    public UndirGraph(EdgeFactory<V, E> ef, boolean allowMultipleEdges, boolean allowLoops) {
        super(ef, allowMultipleEdges, allowLoops);
    }
    public UndirGraph(Class<? extends E> edgeClass, boolean allowMultipleEdges, boolean allowLoops) {
        this(new ClassBasedEdgeFactory<>(edgeClass), allowMultipleEdges, allowLoops);
    }

    @Override
    protected Specifics<V, E> createSpecifics() {
        return super.createUndirectedSpecifics();
    }
}
