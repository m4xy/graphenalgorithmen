package de.fhws.fiw.mis.graph.io.exporter;

import de.fhws.fiw.mis.graph.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.ext.ComponentAttributeProvider;
import org.jgrapht.ext.DOTExporter;
import org.jgrapht.ext.IntegerNameProvider;
import org.jgrapht.ext.StringNameProvider;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by maxarndt on 23.03.17.
 */
public class GraphDotExporterImpl implements GraphFileExporter {
    @Override
    public void exportGraph(Graph<Vertex, DefaultWeightedEdge> graph, String filePath) {
        ComponentAttributeProvider<DefaultWeightedEdge> componentAttributeProvider = new ComponentAttributeProvider<DefaultWeightedEdge>() {
            public Map<String, String> getComponentAttributes(DefaultWeightedEdge e) {
                Map<String, String> map = new LinkedHashMap<>();
                if(graph.getEdgeWeight(e) != 1)
                    map.put("label", new DecimalFormat("#.##").format(graph.getEdgeWeight(e)));
                return map;
            }
        };

        DOTExporter export = new DOTExporter(new IntegerNameProvider<String>(), new StringNameProvider<String>(), null, null, componentAttributeProvider);

        try {
            export.exportGraph(graph, new FileWriter(filePath));
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }
}
