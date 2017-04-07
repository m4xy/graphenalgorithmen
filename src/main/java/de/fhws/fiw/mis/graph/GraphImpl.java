package de.fhws.fiw.mis.graph;

import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.ClassBasedEdgeFactory;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by maxarndt on 06.04.17.
 */
public class GraphImpl extends AbstractBaseGraph<Vertex, DefaultWeightedEdge> {
    public GraphImpl(EdgeFactory<Vertex, DefaultWeightedEdge> ef, boolean allowMultipleEdges, boolean allowLoops) {
        super(ef, allowMultipleEdges, allowLoops);
    }

    public boolean isConnected() {
        Collection<Vertex> vertices = breadthFirstSearch(vertexSet().stream().findFirst().get());
        return !vertexSet().stream().anyMatch(x -> vertices.contains(x) == false);
    }
    public Collection<Vertex> getNeighbors(Vertex vertex) {
        Collection<Vertex> vertices = new ArrayList<>();
        Set<DefaultWeightedEdge> edges = edgesOf(vertex);
        for(DefaultWeightedEdge edge : edges) {
            Vertex neighbor = getEdgeSource(edge);
            if(neighbor.equals(vertex)) neighbor = getEdgeTarget(edge);
            if(!neighbor.equals(vertex)) vertices.add(neighbor);
        }
        return vertices;
    }
    public Collection<Vertex> breadthFirstSearch(Vertex startVertex) {
        Queue<Vertex> queue = new LinkedList<>(Arrays.asList(startVertex));
        List<Vertex> visited = new LinkedList<>(queue);

        while (!queue.isEmpty()) {
            Vertex curVertex = queue.remove();
            Collection<Vertex> neighbors = getNeighbors(curVertex).stream()
                    .distinct()
                    .filter(v -> visited.contains(v) != true)
                    .collect(Collectors.toList());
            queue.addAll(neighbors);
            visited.addAll(neighbors);
        }
        return visited;
    }







}
