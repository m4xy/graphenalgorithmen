package de.fhws.fiw.mis.graph.io.importer;

import de.fhws.fiw.mis.graph.UndirGraph;
import de.fhws.fiw.mis.graph.io.DirGraph;
import org.jgrapht.graph.*;

/**
 * Created by maxarndt on 22.03.17.
 */
public interface GraphImporter {
    UndirGraph<String, DefaultWeightedEdge> importGraph(String fileName);
    DirGraph<String, DefaultWeightedEdge> importDirectedGraph(String fileName);
}
