package de.fhws.fiw.mis.graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by maxarndt on 06.04.17.
 */
public abstract class AbstractGraph implements Graph {
    private boolean allowMultipleEdges;
    private boolean allowLoops;
    private Map<String, Vertex> vertexMap;
    private Map<Vertex, Edge> edgeMap; //warum multi value bei felix?
    private List<Edge> edgeList; //für was?

    public AbstractGraph(boolean allowMultipleEdges, boolean allowLoops) {
        this.allowMultipleEdges = allowMultipleEdges;
        this.allowLoops = allowLoops;
    }

    public abstract Collection<VertexBase> getNeighbors(VertexBase vertex);
    public abstract boolean hasCycle();
    public abstract boolean hasEulerianCircuit();
    public abstract boolean hasEulerianPath();

    @Override
    public Set<EdgeBase> getAllEdges(VertexBase sourceVertex, VertexBase targetVertex) {
        return null;
    }

    @Override
    public EdgeBase getEdge(VertexBase sourceVertex, VertexBase targetVertex) {
        return null;
    }

    @Override
    public EdgeBase addEdge(VertexBase sourceVertex, VertexBase targetVertex) {
        return null;
    }

    @Override
    public boolean addVertex(VertexBase v) {
        return false;
    }

    @Override
    public boolean containsEdge(VertexBase sourceVertex, VertexBase targetVertex) {
        return false;
    }

    @Override
    public boolean containsEdge(EdgeBase e) {
        return false;
    }

    @Override
    public boolean containsVertex(VertexBase v) {
        return false;
    }

    @Override
    public Set<EdgeBase> getEdgeSet() {
        return null;
    }

    @Override
    public Set<EdgeBase> getEdgesOf(VertexBase v) {
        return null;
    }

    @Override
    public EdgeBase removeEdge(VertexBase sourceVertex, VertexBase targetVertex) {
        return null;
    }

    @Override
    public boolean removeEdge(EdgeBase e) {
        return false;
    }

    @Override
    public boolean removeVertex(VertexBase v) {
        return false;
    }

    @Override
    public Set<VertexBase> getVertexSet() {
        return null;
    }

    @Override
    public VertexBase getEdgeSource(EdgeBase e) {
        return null;
    }

    @Override
    public VertexBase getEdgeTarget(EdgeBase e) {
        return null;
    }

    @Override
    public int getEdgeWeight(EdgeBase e) {
        return 0;
    }

    @Override
    public void setEdgeWeight(Edge e, int weight) {

    }

    public boolean isConnected() {
        Collection<VertexBase> vertices = breadthFirstSearch(getVertexSet().stream().findFirst().get());
        return !getVertexSet().stream().anyMatch(x -> vertices.contains(x) == false);
    }
    public boolean hasWeightedEdges() {
        return getEdgeSet().stream().filter(e -> getEdgeWeight(e) != 1.0).count() > 0;
    }

    public Collection<VertexBase> breadthFirstSearch(VertexBase startVertex) {
        Queue<VertexBase> queue = new LinkedList<>(Arrays.asList(startVertex));
        List<VertexBase> visited = new LinkedList<>(queue);

        while (!queue.isEmpty()) {
            VertexBase curVertex = queue.remove();
            Collection<VertexBase> neighbors = getNeighbors(curVertex).stream()
                    .distinct()
                    .filter(v -> visited.contains(v) != true)
                    .collect(Collectors.toList());
            queue.addAll(neighbors);
            visited.addAll(neighbors);
        }
        return visited;
    }

    public Collection<VertexBase> depthFirstSearch(VertexBase startVertex) {
        Stack<VertexBase> stack = new Stack<>();
        List<VertexBase> visited = new LinkedList<>();
        stack.add(startVertex);
        while(!stack.isEmpty()) {
            VertexBase curVertex = stack.pop();
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
