package de.fhws.fiw.mis.graph.io.importer;

import de.fhws.fiw.mis.graph.DirGraph;
import de.fhws.fiw.mis.graph.UndirGraph;
import de.fhws.fiw.mis.graph.Vertex;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by maxarndt on 22.03.17.
 */
public class GraphImporterImpl implements GraphImporter {
    private String searchBase;

    public GraphImporterImpl(String searchbase) {
        this.searchBase = searchbase.endsWith("/") ? searchbase : searchbase + "/";
    }

    @Override
    public UndirGraph importGraph(String fileName) {
        UndirGraph graph = new UndirGraph();
        buildGraph(graph, fileName);

        return graph;
    }

    @Override
    public DirGraph importDirectedGraph(String fileName) {
        DirGraph graph = new DirGraph();
        buildGraph(graph, fileName);

        return graph;
    }

    private void buildGraph(AbstractBaseGraph<Vertex, DefaultWeightedEdge> graph, String fileName) {
        List<String> inputFile = readFile(fileName);

        addVertexes(inputFile, graph);
        addEdges(inputFile, graph);
    }

    private List<String> readFile(String fileName) {
        List<String> stringList = new LinkedList<>();

        try (Stream<String> stream = Files.lines(Paths.get(searchBase + fileName))) {
            stream.forEach(l -> stringList.add(l));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringList;
    }
    private void addVertexes(List<String> inputFile, AbstractBaseGraph<Vertex, DefaultWeightedEdge> graph) {
        for(String line : inputFile) {
            if(line.startsWith("knoten")) {
                String vertexName = line.split(" ")[1];
                graph.addVertex(new Vertex(vertexName));
            }
        }
    }

    private void addEdges(List<String> inputFile, AbstractBaseGraph<Vertex, DefaultWeightedEdge> graph) {
        try {
            for(String line : inputFile) {
                if(line.startsWith("kante")) {
                    String[] lineArr = line.split(" ");
                    Double weight = 1.0;

                    String v1 = lineArr[1];
                    String v2 = lineArr[2];

                    if(lineArr.length > 3) {
                        weight = Double.parseDouble(lineArr[3]);
                    }

                    DefaultWeightedEdge e = graph.addEdge(new Vertex(v1), new Vertex(v2));
                    graph.setEdgeWeight(e, weight);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
}
