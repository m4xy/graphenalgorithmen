package de.fhws.fiw.mis.graph.io.exporter;

import de.fhws.fiw.mis.graph.Edge;
import de.fhws.fiw.mis.graph.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * Created by maxarndt on 23.03.17.
 */
public interface GraphFileExporter {
    void exportGraph(Graph<Vertex, Edge> graph, String filePath);
}
