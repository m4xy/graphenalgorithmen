package de.fhws.fiw.mis.graph;

import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by maxarndt on 06.04.17.
 */
public abstract class AbstractGraph extends AbstractBaseGraph<Vertex, Edge> {
    public AbstractGraph(EdgeFactory<Vertex, Edge> ef, boolean allowMultipleEdges, boolean allowLoops) {
        super(ef, allowMultipleEdges, allowLoops);
    }

    public abstract Collection<Vertex> getNeighbors(Vertex vertex);
    public abstract boolean hasCycle();
    public abstract boolean hasEulerianCircuit();
    public abstract boolean hasEulerianPath();



    public boolean isConnected() {
        Collection<Vertex> vertices = breadthFirstSearch(vertexSet().stream().findFirst().get());
        return !vertexSet().stream().anyMatch(x -> vertices.contains(x) == false);
    }
    public boolean hasWeightedEdges() {
        return edgeSet().stream().filter(e -> getEdgeWeight(e) != 1.0).count() > 0;
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
    public Collection<Vertex> depthFirstSearch(Vertex startVertex) {
        Stack<Vertex> stack = new Stack<>();
        List<Vertex> visited = new LinkedList<>();
        stack.add(startVertex);
        while(!stack.isEmpty()) {
            Vertex curVertex = stack.pop();
            if(!visited.contains(curVertex)) {
                visited.add(curVertex);
                stack.addAll(getNeighbors(curVertex).stream()
                        .distinct()
                        .filter(v -> visited.contains(v) != true)
                        .collect(Collectors.toList()));
            }
        }
        return visited;
    }
}
