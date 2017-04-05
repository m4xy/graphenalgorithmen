package de.fhws.fiw.mis.graph.io.exporter;

import de.fhws.fiw.mis.graph.UndirGraph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * Created by maxarndt on 23.03.17.
 */
public interface GraphExporter {
    void exportGraph(UndirGraph<String, DefaultWeightedEdge> graph, String filePath);
}
