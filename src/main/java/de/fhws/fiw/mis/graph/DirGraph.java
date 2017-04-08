package de.fhws.fiw.mis.graph;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.specifics.Specifics;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by maxarndt on 05.04.17.
 */
public class DirGraph extends AbstractGraph implements DirectedGraph<Vertex, DefaultWeightedEdge>, Euler {
    public DirGraph() {
        super(new ClassBasedEdgeFactory<>(DefaultWeightedEdge.class), false, false);
    }

    @Override
    protected Specifics<Vertex, DefaultWeightedEdge> createSpecifics() {
        return super.createDirectedSpecifics();
    }

    @Override
    public Collection<Vertex> getNeighbors(Vertex vertex) {
        Collection<Vertex> vertices = new ArrayList<>();
        Set<DefaultWeightedEdge> edges = edgesOf(vertex);
        for(DefaultWeightedEdge edge : edges) {
            Vertex neighbor = getEdgeTarget(edge);
            if(!neighbor.equals(vertex)) vertices.add(neighbor);
        }
        return vertices;
    }

    public boolean hasEulerianCircuit() {
        return isConnected() && vertexSet().stream()
                .allMatch(x -> inDegreeOf(x) == outDegreeOf(x));
    }
    public boolean hasEulerianPath() {
        return isConnected() && vertexSet().stream().
                allMatch(v -> Math.abs(inDegreeOf(v) - outDegreeOf(v)) < 2);
    }
    public boolean hasCycle() { //Kahn's Algorithm
        DirGraph clone = (DirGraph)clone();
        List<Vertex> sortedVertices = new LinkedList<>();
        Queue<Vertex> verticesWOIncEdge = new LinkedList<>(clone.vertexSet().stream()
                .filter(v -> clone.inDegreeOf(v) == 0)
                .collect(Collectors.toList()));
        while(!verticesWOIncEdge.isEmpty()) {
            Vertex curVertex = verticesWOIncEdge.remove();
            sortedVertices.add(curVertex);
            clone.edgesOf(curVertex).stream().forEach(e -> {
                Vertex targetVertex = clone.getEdgeTarget(e);
                clone.removeEdge(e);
                if(clone.inDegreeOf(targetVertex) == 0)
                    verticesWOIncEdge.add(targetVertex);
            });
        }

        return clone.edgeSet().size() > 0 ? true : false;
    }
}
