package de.fhws.fiw.mis;

import de.fhws.fiw.mis.graph.UndirGraph;
import de.fhws.fiw.mis.graph.io.dotconverter.DotConverter;
import de.fhws.fiw.mis.graph.io.dotconverter.GraphViz;
import de.fhws.fiw.mis.graph.io.exporter.GraphFileExporter;
import de.fhws.fiw.mis.graph.io.exporter.GraphDotExporterImpl;
import de.fhws.fiw.mis.graph.io.exporter.GraphVisJSExporterImpl;
import de.fhws.fiw.mis.graph.io.importer.GraphImporter;
import de.fhws.fiw.mis.graph.io.importer.GraphImporterImpl;


public class App {

    public static void main(String[] args) {
        final String DOT_FILE_NAME = "graph.dot";

        GraphImporter importer = new GraphImporterImpl("graphs");
        UndirGraph g = importer.importGraph("Baum.txt");
//        DirGraph g = importer.importDirectedGraph("graphs/Test.txt");


        GraphFileExporter exporter = new GraphDotExporterImpl();
        exporter.exportGraph(g, DOT_FILE_NAME);

        DotConverter converter = new GraphViz();
        converter.convertToPNG(DOT_FILE_NAME);
    }
}
