package de.fhws.fiw.mis.graph.io.importer;

import de.fhws.fiw.mis.graph.DirectedBaseGraph;
import de.fhws.fiw.mis.graph.UndirectedBaseGraph;

/**
 * Created by maxarndt on 22.03.17.
 */
public interface GraphImporter {
    UndirectedBaseGraph importGraph(String fileName);
    DirectedBaseGraph importDirectedGraph(String fileName);
}
