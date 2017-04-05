package de.fhws.fiw.mis.graph.io.importer;

import de.fhws.fiw.mis.graph.UndirGraph;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.*;

/**
 * Created by maxarndt on 22.03.17.
 */
public interface GraphImporter {
    UndirGraph<String, DefaultWeightedEdge> importGraph(String fileName);
    SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> importDirectedGraph(String fileName);
}
