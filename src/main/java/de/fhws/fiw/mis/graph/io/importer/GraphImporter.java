package de.fhws.fiw.mis.graph.io.importer;

import de.fhws.fiw.mis.graph.UndirGraph;
import de.fhws.fiw.mis.graph.DirGraph;
import de.fhws.fiw.mis.graph.Vertex;
import org.jgrapht.graph.*;

/**
 * Created by maxarndt on 22.03.17.
 */
public interface GraphImporter {
    UndirGraph importGraph(String fileName);
    DirGraph importDirectedGraph(String fileName);
}
