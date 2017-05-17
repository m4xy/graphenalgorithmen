package de.fhws.fiw.mis;

import de.fhws.fiw.mis.graph.DirectedBaseGraph;
import de.fhws.fiw.mis.graph.UndirectedBaseGraph;
import de.fhws.fiw.mis.graph.VertexBase;
import de.fhws.fiw.mis.graph.color.ColorAlgorithm;
import de.fhws.fiw.mis.graph.color.HtmlColor;
import de.fhws.fiw.mis.graph.io.importer.GraphImporter;
import de.fhws.fiw.mis.graph.io.importer.GraphImporterImpl;

import java.util.Arrays;


public class App {

    public static void main(String[] args) {
        final String DOT_FILE_NAME = "graph.dot";

        GraphImporter importer = new GraphImporterImpl("graphs");
        UndirectedBaseGraph g = importer.importGraph("Dijkstra.txt");
//        DirectedBaseGraph g = importer.importDirectedGraph("Dijkstra.txt");

        ColorAlgorithm.randomizedGreedyCol(g);


        System.out.println();
    }
}
