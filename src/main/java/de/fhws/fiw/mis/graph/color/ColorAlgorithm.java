package de.fhws.fiw.mis.graph.color;

import de.fhws.fiw.mis.graph.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by maxarndt on 27.04.17.
 */
public class ColorAlgorithm {

    public static void greedyCol(AbstractGraph graph) {
        graph.getAllVertices()
                .forEach(v -> v.setColor(HtmlColor.BLACK));

        graph.getAllVertices()
                .forEach(v ->
                        v.setColor(Arrays.stream(HtmlColor.values())
                            .filter(c -> graph.getNeighbors(v).stream()
                                    .map(Vertex::getHtmlColor)
                                    .noneMatch(x -> x.equals(c)))
                            .findFirst().get())
                );
    }
    public static void iteratedGreedyCol(AbstractGraph graph) {
        if(graph instanceof UndirectedBaseGraph) {
            AbstractGraph clone = new UndirectedBaseGraph((UndirectedBaseGraph)graph);
            AbstractGraph bestColoredGraph = new UndirectedBaseGraph((UndirectedBaseGraph)graph);
            long t= System.currentTimeMillis();
            long end = t+5000;
            long amountOfBestGraphColors = getAmountOfVertexColors(bestColoredGraph);
            int i = 1;

            while(System.currentTimeMillis() < end) {
                System.out.println(System.currentTimeMillis() + ": do");
                randomizedGreedyCol(clone);
                long cloneVertexColors = getAmountOfVertexColors(clone);

                if(amountOfBestGraphColors == 0 || cloneVertexColors < amountOfBestGraphColors) {
                    bestColoredGraph = clone;
                    amountOfBestGraphColors = getAmountOfVertexColors(bestColoredGraph);
                }

                i++;

                System.out.println(System.currentTimeMillis() + ": finished with " + cloneVertexColors + " colors. Best: " + amountOfBestGraphColors);
                if(amountOfBestGraphColors < 3)
                    break;
            }

            System.out.println("Anzahl Durchlaeufe: " + i);
            final AbstractGraph referenceGraph = new UndirectedBaseGraph((UndirectedBaseGraph)bestColoredGraph);
            graph.getAllVertices().forEach(v -> v.setColor(referenceGraph.getVertex(v.getName()).getHtmlColor()));
        } else {
            throw new NotImplementedException();
        }
    }
    public static void randomizedGreedyCol(AbstractGraph graph) {
        List<Vertex> vertices = new ArrayList<>(graph.getAllVertices());
        int numberOfVertices = vertices.size();

        vertices.forEach(v -> v.setColor(HtmlColor.BLACK));

        for(int i = 0;i < numberOfVertices;i++) {
            Vertex v = removeRandomVertex(vertices);

            v.setColor(Arrays.stream(HtmlColor.values())
                    .filter(c -> graph.getNeighbors(v).stream()
                            .map(Vertex::getHtmlColor)
                            .noneMatch(x -> x.equals(c)))
                    .findFirst().get());
        }
    }
    public static Vertex removeRandomVertex(List<Vertex> vertices) {
        Vertex v = vertices.get(new Double(Math.random() * vertices.size()).intValue());
        vertices.remove(v);
        return v;
    }
    public static long getAmountOfVertexColors(AbstractGraph graph) {
        return graph.getAllVertices().stream().map(Vertex::getHtmlColor).filter(c -> !c.equals(HtmlColor.WHITE)).distinct().count();
    }

    public static void colorEdges(AbstractGraph graph) {
        if(graph instanceof DirectedBaseGraph) throw new NotImplementedException();

        if(graph.getAllEdges().size() == 0) {
            return;
        }
        else {
            Edge edge = graph.getAllEdges().stream().findFirst().get();
            AbstractGraph clone = new UndirectedBaseGraph((UndirectedBaseGraph)graph);
            clone.removeEdge(edge);
            colorEdges(clone);
            if(clone.getDegree() < graph.getDegree()) {
                edge.setColor(Arrays.asList(HtmlColor.values()).stream()
                        .filter(c -> graph.getEdgesOf(edge.getSource()).stream()
                                    .noneMatch(x -> x.getColor().getColor().equals(c))
                                && graph.getEdgesOf(edge.getTarget()).stream()
                                .noneMatch(x -> x.getColor().getColor().equals(c)))
                        .findFirst().get());
            }
            else {
                HtmlColor col = lemma2_10(clone, edge.getSource(), edge.getTarget());
                if(col != null)
                    edge.setColor(col);
            }
        }
    }
    public static HtmlColor lemma2_10(AbstractGraph graph, Vertex u, Vertex v) {
        int i = 0;
        HtmlColor c0 = Arrays.asList(HtmlColor.values()).stream()
                .filter(c -> graph.getEdgesOf(v).stream()
                        .noneMatch(x -> x.getColor().getColor().equals(c)))
                .findFirst().get();

        List<Edge> edgesOfU = graph.getEdgesOf(u).stream().collect(Collectors.toList());

        while(edgesOfU.size() > i && edgesOfU.get(i).getColor().getColor().ordinal() == i) {
            final int j = i;
            HtmlColor missingColor = getFirstMissingColor(graph.getEdgesOf(edgesOfU.get(i).getTarget().equals(u) ? edgesOfU.get(i).getSource() : edgesOfU.get(i).getTarget()));

            graph.getAllEdges().stream().filter(e -> e.getColor().getColor().ordinal() == j + 1)
                    .forEach(x -> x.setColor(missingColor));

            i++;
        }
        return c0;
    }
    public static boolean isAnyEdgeSpecificColored(List<Edge> edges, int htmlColorIndex) {
        return edges.stream().anyMatch(c -> c.getColor().getColor().ordinal() == htmlColorIndex);
    }
    public static HtmlColor getFirstMissingColor(Set<Edge> edges) {
        return Arrays.asList(HtmlColor.values()).stream()
                .filter(c -> edges.stream()
                        .noneMatch(x -> x.getColor().getColor().equals(c)))
                .findFirst().get();
    }
}
