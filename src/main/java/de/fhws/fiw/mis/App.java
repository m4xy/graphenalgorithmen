package de.fhws.fiw.mis;

import de.fhws.fiw.mis.graph.DirGraph;
import de.fhws.fiw.mis.graph.Euler;
import de.fhws.fiw.mis.graph.UndirGraph;
import de.fhws.fiw.mis.graph.Vertex;
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
        UndirGraph<Vertex, DefaultWeightedEdge> g = importer.importGraph("Eulerpfad.txt");
//        DirGraph<Vertex, DefaultWeightedEdge> g = importer.importDirectedGraph("K5.txt");

//        System.out.println(Euler.hasEulerianCircuit(g));
        System.out.println(Euler.hasEulerianPath(g));


        GraphExporter exporter = new GraphExporterImpl();
        exporter.exportGraph(g, DOT_FILE_NAME);

        DotConverter converter = new GraphViz();
        converter.convertToPNG(DOT_FILE_NAME);
    }
}
