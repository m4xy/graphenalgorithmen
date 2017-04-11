package de.fhws.fiw.mis.graph.io.exporter;

import de.fhws.fiw.mis.graph.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * Created by maxarndt on 11.04.17.
 */
public interface GraphStringExporter {
    String exportGraph(Graph<Vertex, DefaultWeightedEdge> graph);
}