package de.fhws.fiw.mis.graph;


import com.google.common.collect.ArrayListMultimap;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by maxarndt on 05.04.17.
 */
public class DirectedBaseGraph extends AbstractGraph implements DirectedGraph, Serializable {

    public DirectedBaseGraph() {
    }
    public DirectedBaseGraph(DirectedBaseGraph clone) {
        this.edgeList = new ArrayList<Edge>(clone.edgeList);
        this.edgeMap = ArrayListMultimap.create(clone.edgeMap);
        this.vertexMap = new HashMap<>(clone.vertexMap);
    }

    @Override
    public Set<Edge> getEdges(Vertex sourceVertex, Vertex targetVertex) {
        return new HashSet<>(super.edgeMap.get(sourceVertex).stream()
                .filter(e -> e.getTarget().equals(targetVertex) && e.getSource().equals(sourceVertex))
                .collect(Collectors.toList()));
    }

    @Override
    public boolean containsEdge(Vertex sourceVertex, Vertex targetVertex) {
        return super.edgeMap.get(sourceVertex).stream()
                .filter(e -> e.getTarget().equals(targetVertex) && e.getSource().equals(sourceVertex))
                .count() > 0;
    }

    @Override
    public int getInDegreeOf(Vertex v) {
        return (int)super.edgeMap.get(v).stream().filter(e -> e.getTarget().equals(v)).count();
    }

    @Override
    public Set<Edge> getIncomingEdgesOf(Vertex v) {
        return new HashSet<>(super.edgeMap.get(v).stream().filter(e -> e.getTarget().equals(v)).collect(Collectors.toList()));
    }

    @Override
    public int getOutDegreeOf(Vertex v) {
        return (int)super.edgeMap.get(v).stream().filter(e -> e.getSource().equals(v)).count();
    }

    @Override
    public Set<Edge> getOutgoingEdgesOf(Vertex v) {
        return new HashSet<>(super.edgeMap.get(v).stream().filter(e -> e.getSource().equals(v)).collect(Collectors.toList()));
    }

    @Override
    public Collection<Vertex> getNeighbors(Vertex vertex) {
        Collection<Vertex> vertices = new ArrayList<>();
        Set<Edge> edges = getEdgesOf(vertex);
        for(Edge edge : edges) {
            Vertex neighbor = edge.getTarget();
            if(!neighbor.equals(vertex)) vertices.add(neighbor);
        }
        return vertices;
    }

    @Override
    public boolean hasEulerianCircuit() {
        return isConnected() && getAllVertices().stream()
                .allMatch(x -> getInDegreeOf(x) == getOutDegreeOf(x));
    }

    @Override
    public boolean hasEulerianPath() {
        return isConnected() && getAllVertices().stream().
                allMatch(v -> Math.abs(getInDegreeOf(v) - getOutDegreeOf(v)) < 2);
    }

    @Override
    public boolean hasCycle() { //Kahn's Algorithm
        DirectedBaseGraph clone = new DirectedBaseGraph(this);
        List<Vertex> sortedVertices = new LinkedList<>();
        Queue<Vertex> verticesWOIncEdge = new LinkedList<>(clone.getAllVertices().stream()
                .filter(v -> clone.getInDegreeOf(v) == 0)
                .collect(Collectors.toList()));
        while(!verticesWOIncEdge.isEmpty()) {
            Vertex curVertex = verticesWOIncEdge.remove();
            sortedVertices.add(curVertex);
            clone.getEdgesOf(curVertex).stream().forEach(e -> {
                Vertex targetVertex = e.getTarget();
                clone.removeEdge(e);
                if(clone.getInDegreeOf(targetVertex) == 0)
                    verticesWOIncEdge.add(targetVertex);
            });
        }

        return clone.getAllEdges().size() > 0;
    }

    @Override
    public int getMaxFlow(Vertex source, Vertex sink) {
        int maxFlow = 0;
        List<Edge> path;

        getAllEdges().stream().forEach(e -> {
            addEdge(new FlowEdge(e));
            removeEdge(e);
        });

        while(!(path = getPath(source, sink)).isEmpty()) {
            int adjustment = path.stream().min(Comparator.comparingInt(Edge::getWeight)).get().getWeight();

            maxFlow += adjustment;
            path.stream().forEach(e -> {
                e.setWeight(e.getWeight() - adjustment);
                if(containsEdge(e.getTarget(), e.getSource())) {
                    getEdges(e.getTarget(), e.getSource()).stream().findFirst().get().incrementWeight(adjustment);
                } else {
                    addEdge(e.getTarget(), e.getSource()).setWeight(adjustment);
                }
            });
        }

        return maxFlow;
    }

    public List<Edge> getPath(Vertex source, Vertex sink) {
        List<Edge> path = new LinkedList<>();
        Queue<Edge> queue = new LinkedList<>();
        Set<Vertex> visited = new HashSet<>();

        Edge initialEdge = getOutgoingEdgesOf(source).stream().filter(e -> e.getWeight() > 0).findFirst().orElse(null);
        if(initialEdge != null) {
            queue.add(initialEdge);
        }

        while(!queue.isEmpty()) {
            Edge currentEdge = queue.poll();

            if (currentEdge != null && currentEdge.getWeight() <= 0
                    || visited.contains(currentEdge.getTarget())
                    || (path.size() > 0 && !currentEdge.getSource().equals(path.get(path.size()-1).getTarget())))
                continue;

            visited.add(currentEdge.getSource());
            path.add(currentEdge);

            if (currentEdge.getTarget().equals(sink))
                break;

            queue.addAll(getOutgoingEdgesOf(currentEdge.getTarget()));
        }

        if(path.size() > 0 && !sink.equals(path.get(path.size()-1).getTarget())) {
            path.get(path.size()-1).setWeight(0);
            return getPath(source, sink);
        }

        return path;
    }
}

class VertexEdgeTupel {
    private Vertex vertex;
    private Edge edge;

    public VertexEdgeTupel(Vertex vertex, Edge edge) {
        this.vertex = vertex;
        this.edge = edge;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    public Edge getEdge() {
        return edge;
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
    }
}
