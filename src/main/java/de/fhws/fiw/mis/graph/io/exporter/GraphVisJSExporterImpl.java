package de.fhws.fiw.mis.graph.io.exporter;

import de.fhws.fiw.mis.graph.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * Created by maxarndt on 11.04.17.
 */
public class GraphVisJSExporterImpl implements GraphVisJSExporter {
    private Graph<Vertex, DefaultWeightedEdge> graph;

    public GraphVisJSExporterImpl(Graph<Vertex, DefaultWeightedEdge> graph) {
        this.graph = graph;
    }

    @Override
    public String getNodeDataSet() {
        StringBuilder sb = new StringBuilder();
        graph.vertexSet().stream()
                .forEach(v -> sb.append(getNodeObj(v.getName(), v.toString())));

        return removeLastChar(sb.toString());
    }

    @Override
    public String getEdgeDataSet() {
        StringBuilder sb = new StringBuilder();
        graph.edgeSet().stream()
                .forEach(e -> sb.append(getEdgeObj(graph.getEdgeSource(e).getName(), graph.getEdgeTarget(e).getName())));

        return removeLastChar(sb.toString());
    }


    private String getNodeObj(String id, String label) {
        return "{id: '" + id + "', label: '" + label + "'},";
    }
    private String getEdgeObj(String from, String to) {
        return "{from: '" + from + "', to: '" + to + "'},";
    }
    private String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
}
