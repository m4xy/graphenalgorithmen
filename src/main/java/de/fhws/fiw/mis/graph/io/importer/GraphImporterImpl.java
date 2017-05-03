package de.fhws.fiw.mis.graph.io.importer;

import de.fhws.fiw.mis.graph.*;

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
    public UndirectedBaseGraph importGraph(String fileName) {
        UndirectedBaseGraph graph = new UndirectedBaseGraph();
        buildGraph(graph, fileName);

        return graph;
    }

    @Override
    public DirectedBaseGraph importDirectedGraph(String fileName) {
        DirectedBaseGraph graph = new DirectedBaseGraph();
        buildGraph(graph, fileName);

        return graph;
    }

    private void buildGraph(AbstractGraph graph, String fileName) {
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
    private void addVertexes(List<String> inputFile, AbstractGraph graph) {
        for(String line : inputFile) {
            if(line.startsWith("knoten")) {
                String vertexName = line.split(" ")[1];
                graph.addVertex(new VertexBase(vertexName));
            }
        }
    }

    private void addEdges(List<String> inputFile, AbstractGraph graph) {
        try {
            for(String line : inputFile) {
                if(line.startsWith("kante")) {
                    String[] lineArr = line.split(" ");
                    int weight = 1;

                    String v1 = lineArr[1];
                    String v2 = lineArr[2];

                    if(lineArr.length > 3) {
                        weight = Integer.parseInt(lineArr[3]);
                    }

                    graph.addEdge(v1, v2, weight);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
}
