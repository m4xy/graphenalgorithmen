package de.fhws.fiw.mis.graph.io.importer;

import de.fhws.fiw.mis.graph.DirGraph;
import de.fhws.fiw.mis.graph.UndirGraph;

/**
 * Created by maxarndt on 22.03.17.
 */
public interface GraphImporter {
    UndirGraph importGraph(String fileName);
    DirGraph importDirectedGraph(String fileName);
}
