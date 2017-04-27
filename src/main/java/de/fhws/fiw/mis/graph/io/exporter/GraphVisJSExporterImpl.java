package de.fhws.fiw.mis.graph.io.exporter;

import de.fhws.fiw.mis.graph.AbstractGraph;
import de.fhws.fiw.mis.graph.Edge;

import java.text.DecimalFormat;

/**
 * Created by maxarndt on 11.04.17.
 */
public class GraphVisJSExporterImpl implements GraphVisJSExporter {
    private AbstractGraph graph;
    private boolean hasWeightedEdges;

    public GraphVisJSExporterImpl(AbstractGraph graph) {
        this.graph = graph;
        this.hasWeightedEdges = graph.hasWeightedEdges();
    }

    @Override
    public String getNodeDataSet() {
        StringBuilder sb = new StringBuilder();
        graph.getAllVertices().stream()
                .forEach(v -> sb.append(getNodeObj(v.getName(), v.toString())));

        return removeLastChar(sb.toString());
    }

    @Override
    public String getEdgeDataSet(boolean arrows) {
        StringBuilder sb = new StringBuilder();
        graph.getAllEdges().stream()
                .forEach(e -> sb.append(getEdgeObj(e, hasWeightedEdges, arrows)));

        return removeLastChar(sb.toString());
    }


    private String getNodeObj(String id, String label) {
        return "{id: '" + id + "', label: '" + label + "'},";
    }
    private String getEdgeObj(Edge e, boolean weighted, boolean arrows) {
        String edgeObj = "{from: '" + e.getSource().getName() + "', to: '" + e.getTarget().getName() + "', color: '" + e.getColor() + "'";
        edgeObj += weighted ? ", label: '" + e.getWeight() + "', font: {align: 'horizontal'}" : "";
        edgeObj += arrows ? ", arrows: 'to'}," : "},";
        return edgeObj;
    }
    private String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
}
