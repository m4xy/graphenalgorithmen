package de.fhws.fiw.mis.graph;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by maxarndt on 06.04.17.
 */
public abstract class AbstractGraph implements Graph {
    private Map<String, Vertex> vertexMap;
    private Multimap<Vertex, Edge> edgeMap;
    private List<Edge> edgeList;

    public AbstractGraph() {
        vertexMap = new HashMap<>();
        edgeMap = ArrayListMultimap.create();
        edgeList = new ArrayList<>();
    }

    public abstract Collection<Vertex> getNeighbors(Vertex vertex);
    public abstract boolean hasCycle();
    public abstract boolean hasEulerianCircuit();
    public abstract boolean hasEulerianPath();
    public abstract Set<Edge> getAllEdges(Vertex sourceVertex, Vertex targetVertex);
    public abstract Edge getEdge(Vertex sourceVertex, Vertex targetVertex);

    @Override
    public Edge addEdge(Vertex sourceVertex, Vertex targetVertex) {
        Edge e = new EdgeBase(sourceVertex, targetVertex);
        addEdge(e);
        return e;
    }

    @Override
    public Edge addEdge(Vertex sourceVertex, Vertex targetVertex, int weight) {
        Edge e = new EdgeBase(sourceVertex, targetVertex, weight);
        addEdge(e);
        return e;
    }

    @Override
    public void addEdge(Edge e) {
        addVertex(e.getSource());
        addVertex(e.getTarget());

        edgeMap.put(e.getSource(), e);
        if(e.getSource() != e.getTarget())
            edgeMap.put(e.getTarget(), e);

        edgeList.add(e);
    }

    @Override
    public void addVertex(Vertex v) {
        vertexMap.put(v.getName(), v);
    }

    @Override
    public abstract boolean containsEdge(Vertex sourceVertex, Vertex targetVertex);

    @Override
    public boolean containsEdge(Edge e) {
        return edgeList.contains(e);
    }

    @Override
    public boolean containsVertex(Vertex v) {
        return vertexMap.containsKey(v.getName());
    }

    @Override
    public Set<Edge> getEdgeSet() {
        return null;
    }

    @Override
    public Set<Edge> getEdgesOf(Vertex v) {
        return null;
    }

    @Override
    public Edge removeEdge(Vertex sourceVertex, Vertex targetVertex) {
        return null;
    }

    @Override
    public void removeEdge(Edge e) {

    }

    @Override
    public void removeVertex(Vertex v) {
        edgeMap.get(v).stream().forEach(e -> {
            edgeList.remove(e);
            edgeMap.remove(e.getSource(), e);
            edgeMap.remove(e.getTarget(), e);
        });

        vertexMap.remove(v.getName());
    }

    @Override
    public Set<Vertex> getVertexSet() {
        return null;
    }

    @Override
    public Vertex getEdgeSource(Edge e) {
        return null;
    }

    @Override
    public Vertex getEdgeTarget(Edge e) {
        return null;
    }

    @Override
    public int getEdgeWeight(Edge e) {
        return 0;
    }

    @Override
    public void setEdgeWeight(Edge e, int weight) {

    }

    public boolean isConnected() {
        Collection<Vertex> vertices = breadthFirstSearch(getVertexSet().stream().findFirst().get());
        return !getVertexSet().stream().anyMatch(x -> vertices.contains(x) == false);
    }
    public boolean hasWeightedEdges() {
        return getEdgeSet().stream().filter(e -> getEdgeWeight(e) != 1.0).count() > 0;
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
