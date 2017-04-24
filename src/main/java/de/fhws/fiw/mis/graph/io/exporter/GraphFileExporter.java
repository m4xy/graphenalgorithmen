package de.fhws.fiw.mis.graph.io.exporter;

import de.fhws.fiw.mis.graph.Graph;

/**
 * Created by maxarndt on 23.03.17.
 */
public interface GraphFileExporter {
    void exportGraph(Graph graph, String filePath);
}
