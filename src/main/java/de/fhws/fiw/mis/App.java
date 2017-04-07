package de.fhws.fiw.mis;

import de.fhws.fiw.mis.graph.UndirGraph;
import de.fhws.fiw.mis.graph.io.dotconverter.DotConverter;
import de.fhws.fiw.mis.graph.io.dotconverter.GraphViz;
import de.fhws.fiw.mis.graph.io.exporter.GraphExporter;
import de.fhws.fiw.mis.graph.io.exporter.GraphExporterImpl;
import de.fhws.fiw.mis.graph.io.importer.GraphImporter;
import de.fhws.fiw.mis.graph.io.importer.GraphImporterImpl;


public class App {

    public static void main(String[] args) {
        final String DOT_FILE_NAME = "graph.dot";

        GraphImporter importer = new GraphImporterImpl();
        UndirGraph g = importer.importGraph("EulerPfad.txt");
//        DirGraph g = importer.importDirectedGraph("K5.txt");

//        System.out.println(g.hasEulerianCircuit());
//        System.out.println(g.hasEulerianPath());
//        g.breadthFirstSearch(g.vertexSet().stream().findFirst().get()).stream().forEach(System.out::println);
        System.out.println(g.isConnected());

        GraphExporter exporter = new GraphExporterImpl();
        exporter.exportGraph(g, DOT_FILE_NAME);

        DotConverter converter = new GraphViz();
        converter.convertToPNG(DOT_FILE_NAME);
    }
}
