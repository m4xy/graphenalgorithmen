package de.fhws.fiw.mis;

import de.fhws.fiw.mis.graph.UndirGraph;
import de.fhws.fiw.mis.graph.io.DirGraph;
import de.fhws.fiw.mis.graph.io.dotconverter.DotConverter;
import de.fhws.fiw.mis.graph.io.dotconverter.GraphViz;
import de.fhws.fiw.mis.graph.io.exporter.GraphExporter;
import de.fhws.fiw.mis.graph.io.exporter.GraphExporterImpl;
import de.fhws.fiw.mis.graph.io.importer.GraphImporter;
import de.fhws.fiw.mis.graph.io.importer.GraphImporterImpl;
import org.jgrapht.graph.*;


public class App {

    public static void main(String[] args) {
        final String DOT_FILE_NAME = "graph.dot";

        GraphImporter importer = new GraphImporterImpl();
        DirGraph<String, DefaultWeightedEdge> g = importer.importDirectedGraph("Euler1.txt");

//        g.edgesOf("");
//        g.get

        GraphExporter exporter = new GraphExporterImpl();
        exporter.exportGraph(g, DOT_FILE_NAME);

        DotConverter converter = new GraphViz();
        converter.convertToPNG(DOT_FILE_NAME);

        System.out.println(g);
    }
}
