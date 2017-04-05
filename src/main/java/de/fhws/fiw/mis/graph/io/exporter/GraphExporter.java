package de.fhws.fiw.mis.graph.io.exporter;

import de.fhws.fiw.mis.graph.UndirGraph;
import de.fhws.fiw.mis.graph.io.DirGraph;
import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * Created by maxarndt on 23.03.17.
 */
public interface GraphExporter {
    void exportGraph(Graph<String, DefaultWeightedEdge> graph, String filePath);
}
