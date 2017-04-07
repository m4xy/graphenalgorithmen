package de.fhws.fiw.mis.graph;

import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.ClassBasedEdgeFactory;

/**
 * Created by maxarndt on 06.04.17.
 */
public class GraphImpl<V, E> extends AbstractBaseGraph<V, E> {
    public GraphImpl(EdgeFactory<V, E> ef, boolean allowMultipleEdges, boolean allowLoops) {
        super(ef, allowMultipleEdges, allowLoops);
    }
    public GraphImpl(Class<? extends E> edgeClass, boolean allowMultipleEdges, boolean allowLoops) {
        this(new ClassBasedEdgeFactory<>(edgeClass), allowMultipleEdges, allowLoops);
    }

    public boolean isConnected() {
        return false;
    }
}
