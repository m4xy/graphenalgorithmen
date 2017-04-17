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

        GraphImporter importer = new GraphImporterImpl();
        UndirGraph g = importer.importGraph("Baum.txt");
//        DirGraph g = importer.importDirectedGraph("graphs/Test.txt");

//        System.out.println(g.hasEulerianCircuit());
//        System.out.println(g.hasEulerianPath());
//        System.out.println(g.hasCycle());
//        g.breadthFirstSearch(g.vertexSet().stream().findFirst().get()).stream().forEach(System.out::println);
//        g.depthFirstSearch(g.vertexSet().stream().findFirst().get()).stream().forEach(System.out::println);
//        System.out.println(g.hasCycle());

        GraphVisJSExporterImpl exp = new GraphVisJSExporterImpl(g);
        System.out.println(exp.getNodeDataSet());

        GraphFileExporter exporter = new GraphDotExporterImpl();
        exporter.exportGraph(g, DOT_FILE_NAME);

        DotConverter converter = new GraphViz();
        converter.convertToPNG(DOT_FILE_NAME);
    }
}
