package de.fhws.fiw.mis.graph.io.exporter;

import de.fhws.fiw.mis.graph.EdgeBase;
import de.fhws.fiw.mis.graph.VertexBase;
import org.jgrapht.Graph;

/**
 * Created by maxarndt on 23.03.17.
 */
public interface GraphFileExporter {
    void exportGraph(Graph<VertexBase, EdgeBase> graph, String filePath);
}
