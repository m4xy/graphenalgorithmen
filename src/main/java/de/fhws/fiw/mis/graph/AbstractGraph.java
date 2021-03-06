package de.fhws.fiw.mis.graph;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by maxarndt on 06.04.17.
 */
public abstract class AbstractGraph implements Graph, Serializable {
    Map<String, Vertex> vertexMap;
    Multimap<Vertex, Edge> edgeMap;
    List<Edge> edgeList;

    public AbstractGraph() {
        vertexMap = new HashMap<>();
        edgeMap = ArrayListMultimap.create();
        edgeList = new ArrayList<>();
    }

    public abstract Collection<Vertex> getNeighbors(Vertex vertex);
    public abstract boolean hasCycle();
    public abstract boolean hasEulerianCircuit();
    public abstract boolean hasEulerianPath();
    public abstract Set<Edge> getEdges(Vertex sourceVertex, Vertex targetVertex);
    public abstract boolean containsEdge(Vertex sourceVertex, Vertex targetVertex);
    public abstract int getMaxFlow(Vertex source, Vertex sink);

    @Override
    public Edge addEdge(String sourceVertexName, String targetVertexName) {
        Edge e = new EdgeBase(getVertex(sourceVertexName), getVertex(targetVertexName));
        addEdge(e);
        return e;
    }

    @Override
    public Edge addEdge(String sourceVertexName, String targetVertexName, int weight) {
        Edge e = new EdgeBase(getVertex(sourceVertexName), getVertex(targetVertexName), weight);
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
    public boolean containsEdge(Edge e) {
        return edgeList.contains(e);
    }

    @Override
    public boolean containsVertex(Vertex v) {
        return vertexMap.containsKey(v.getName());
    }

    @Override
    public boolean containsVertex(String name) {
        return vertexMap.containsKey(name);
    }

    @Override
    public Set<Edge> getEdges(String sourceVertexName, String targetVertexName) {
        return getEdges(getVertex(sourceVertexName), getVertex(targetVertexName));
    }

    @Override
    public Set<Edge> getAllEdges() {
        return new HashSet<>(edgeList);
    }

    @Override
    public Set<Edge> getEdgesOf(Vertex v) {
        return new HashSet<>(edgeMap.get(v));
    }

    @Override
    public void removeEdges(Vertex sourceVertex, Vertex targetVertex) {
        getEdges(sourceVertex, targetVertex).forEach(e -> removeEdge(e));
    }

    @Override
    public void removeEdge(Edge e) {
        edgeMap.remove(e.getSource(), e);
        edgeMap.remove(e.getTarget(), e);
        edgeList.remove(e);
    }

    @Override
    public void removeVertex(Vertex v) {
        for (Iterator<Edge> i = edgeMap.get(v).iterator(); i.hasNext();) {
            Edge e = i.next();
            edgeMap.remove(e.getSource(), e);
            edgeMap.remove(e.getTarget(), e);
            edgeList.remove(e);
        }
        vertexMap.remove(v.getName());
    }

    @Override
    public Vertex getVertex(String name) {
        if(containsVertex(name))
            return vertexMap.get(name);
        else
            throw new NoSuchElementException("Vertex '" + name + "' was not found!");

    }

    @Override
    public Set<Vertex> getAllVertices() {
        return new HashSet<>(vertexMap.values());
    }

    @Override
    public int getDegree() {
        return getAllVertices().stream().map(v -> getEdgesOf(v).size()).max(Integer::compareTo).get();
    }

    @Override
    public boolean isConnected() {
        Collection<Vertex> vertices = breadthFirstSearch(getAllVertices().stream().findFirst().get());
        return !getAllVertices().stream().anyMatch(x -> vertices.contains(x) == false);
    }

    @Override
    public boolean hasWeightedEdges() {
        return getAllEdges().stream().filter(e -> e.getWeight() != 1).count() > 0;
    }

    @Override
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

    @Override
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
