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

    public static HtmlColor lemma210(AbstractGraph graph, Vertex u, Vertex v) {
        int i = 0;
        List<HtmlColor> colorList = Arrays.asList(Arrays.stream(HtmlColor.values()) //hält an den v_i fehlenden Farben. An unprocessedNeighbors[0] fehlt colorList[1]
                .filter(c -> graph.getEdgesOf(v).stream()
                        .noneMatch(x -> x.getColor().getColor().equals(c)))
                .findFirst().get());

        List<Edge> unprocessedNeighbors = graph.getEdgesOf(u).stream().filter(e -> e.getTarget().equals(v) || e.getSource().equals(v)).collect(Collectors.toList());
        List<Edge> processedNeighbors = new ArrayList<>();



        //a
        //wenn an v_i eine Farbe fehlt, die an u fehlt, bis zu diesem Knoten alle Farben umfärben und die fehlende Farbe genau für diese Kante nutzen.


        while(true) {
            Optional<Edge> e = unprocessedNeighbors.stream().filter(x -> x.getColor().getColor().equals(colorList.get(i))).findFirst();
            if(!e.isPresent()) {
                //rollback algo
                break;
            }
            else if(processedNeighbors.contains(e.get())) {
                //prblemkante entfernen und dann rollback
                //+ richtige Farbe finden für Problemkante
                break;
            }
            else {


                Vertex v_i = e.get().getSource().equals(u) ? e.get().getTarget() : e.get().getSource();
                HtmlColor missingColor = Arrays.stream(HtmlColor.values())
                        .filter(c -> graph.getEdgesOf(v_i).stream()
                                .noneMatch(x -> x.getColor().getColor().equals(c)))
                        .findFirst().get();
                colorList.add(missingColor);
                processedNeighbors.add(e.get());
            }


        }

        return colorList.get(0);
    }
}
