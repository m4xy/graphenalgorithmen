package de.fhws.fiw.mis.graph.color;

import de.fhws.fiw.mis.graph.AbstractGraph;

import java.util.Arrays;

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
                            .filter(c -> graph.getNeighbors(v).stream()
                                    .map(w -> w.getHtmlColor())
                                    .noneMatch(x -> x.equals(c)))
                            .findFirst().get())
                );
    }

    public static void colorEdges(AbstractGraph graph) {

    }
}
