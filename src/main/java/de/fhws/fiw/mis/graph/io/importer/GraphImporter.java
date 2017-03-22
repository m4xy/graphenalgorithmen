package de.fhws.fiw.mis.graph.io.importer;

import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

/**
 * Created by maxarndt on 22.03.17.
 */
public interface GraphImporter {
    SimpleWeightedGraph<String, DefaultWeightedEdge> importGraph(String fileName);
}
