package de.fhws.fiw.mis.graph.io.importer;

import de.fhws.fiw.mis.graph.io.importer.GraphImporter;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by maxarndt on 22.03.17.
 */
public class GraphImporterImpl implements GraphImporter {
    @Override
    public SimpleWeightedGraph<String, DefaultWeightedEdge> importGraph(String fileName) {
        SimpleWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        buildGraph(graph, fileName);

        return graph;
    }

    @Override
    public SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> importDirectedGraph(String fileName) {
        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        buildGraph(graph, fileName);

        return graph;
    }

    private void buildGraph(AbstractBaseGraph<String, DefaultWeightedEdge> graph, String fileName) {
        List<String> inputFile = readFile(fileName);

        addVertexes(inputFile, graph);
        addEdges(inputFile, graph);
    }

    private List<String> readFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        List<String> stringList = new LinkedList<>();

        try {
            File file = new File(classLoader.getResource(fileName).getFile());

            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                String line = br.readLine();

                while (line != null) {
                    stringList.add(line);
                    line = br.readLine();
                }

            } finally {
                br.close();
            }
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }

        return stringList;
    }
    private void addVertexes(List<String> inputFile, AbstractBaseGraph<String, DefaultWeightedEdge> graph) {
        for(String line : inputFile) {
            if(line.startsWith("knoten")) {
                String vertex = line.split(" ")[1];
                graph.addVertex(vertex);
            }
        }
    }

    private void addEdges(List<String> inputFile, AbstractBaseGraph<String, DefaultWeightedEdge> graph) {
        for(String line : inputFile) {
            if(line.startsWith("kante")) {
                String[] lineArr = line.split(" ");
                Double weight = 1.0;

                String v1 = lineArr[1];
                String v2 = lineArr[2];

                if(lineArr.length > 3) {
                    weight = Double.parseDouble(lineArr[3]);
                }

                DefaultWeightedEdge e = graph.addEdge(v1, v2);
                graph.setEdgeWeight(e, weight);
            }
        }
    }
}
