package de.fhws.fiw.mis.graph.io.importer;

import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.*;

/**
 * Created by maxarndt on 22.03.17.
 */
public interface GraphImporter {
    SimpleWeightedGraph<String, DefaultWeightedEdge> importGraph(String fileName);
    SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> importDirectedGraph(String fileName);
}
