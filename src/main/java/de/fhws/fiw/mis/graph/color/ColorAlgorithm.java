package de.fhws.fiw.mis.graph.color;

import de.fhws.fiw.mis.graph.AbstractGraph;
import de.fhws.fiw.mis.graph.Vertex;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by maxarndt on 27.04.17.
 */
public class ColorAlgorithm {

    public static void greedyCol(AbstractGraph graph) {
        graph.getAllVertices().stream()
                .forEach(v -> v.setColor(HtmlColor.BLACK));

        graph.getAllVertices().stream()
                .forEach(v ->
                        v.setColor(Arrays.asList(HtmlColor.values()).stream()
                            .filter(c -> !graph.getNeighbors(v).stream()
                                    .map(w -> w.getHtmlColor())
                                    .collect(Collectors.toList())
                                    .contains(c))
                            .findFirst().get())
                );
    }
}
