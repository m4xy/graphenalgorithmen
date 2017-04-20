package de.fhws.fiw.mis.graph.io.exporter;

/**
 * Created by maxarndt on 11.04.17.
 */
public interface GraphVisJSExporter {
    String getNodeDataSet();
    String getEdgeDataSet(boolean arrows);
}
