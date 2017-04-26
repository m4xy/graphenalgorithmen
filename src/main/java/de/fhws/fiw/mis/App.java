package de.fhws.fiw.mis;

import de.fhws.fiw.mis.graph.DirectedBaseGraph;
import de.fhws.fiw.mis.graph.UndirectedBaseGraph;
import de.fhws.fiw.mis.graph.VertexBase;
import de.fhws.fiw.mis.graph.io.importer.GraphImporter;
import de.fhws.fiw.mis.graph.io.importer.GraphImporterImpl;


public class App {

    public static void main(String[] args) {
        final String DOT_FILE_NAME = "graph.dot";

        GraphImporter importer = new GraphImporterImpl("graphs");
//        UndirectedBaseGraph g = importer.importGraph("Baum.txt");
        DirectedBaseGraph g = importer.importDirectedGraph("Fluss2.txt");

        System.out.println(g.getMaxFlow(new VertexBase("S"), new VertexBase("T")));
    }
}
