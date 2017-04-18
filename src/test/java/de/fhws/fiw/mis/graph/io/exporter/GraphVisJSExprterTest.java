package de.fhws.fiw.mis.graph.io.exporter;


import static org.junit.Assert.*;

import de.fhws.fiw.mis.graph.UndirGraph;
import de.fhws.fiw.mis.graph.Vertex;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by maxarndt on 11.04.17.
 */
public class GraphVisJSExprterTest {
    GraphVisJSExporter exporter;
    UndirGraph g;

    @Before
    public void setUp() {
        g = new UndirGraph();
        g.addVertex(new Vertex("A"));
        g.addVertex(new Vertex("B"));
        g.addVertex(new Vertex("C"));

        g.addEdge(new Vertex("A"), new Vertex("C"));
    }

    @Test
    public void testGetNodeDataSet() {
        exporter = new GraphVisJSExporterImpl(g);
        assertEquals("{id: 'A', label: 'A'},{id: 'B', label: 'B'},{id: 'C', label: 'C'}", exporter.getNodeDataSet());
    }

    @Test
    public void testGetEdgeDataSet() {
        exporter = new GraphVisJSExporterImpl(g);
        assertEquals("{from: 'A', to: 'C'}", exporter.getEdgeDataSet(false));
    }

    @Test
    public void testGetEdgeDataSetWithArrows() {
        exporter = new GraphVisJSExporterImpl(g);
        assertEquals("{from: 'A', to: 'C', arrows: 'to'}", exporter.getEdgeDataSet(true));
    }

    @Test
    public void testGetEdgeDataSetWithLabel() {
        g.setEdgeWeight(g.getEdge(new Vertex("A"), new Vertex("C")), 7.0);
        exporter = new GraphVisJSExporterImpl(g);
        assertEquals("{from: 'A', to: 'C', label: '7', font: {align: 'horizontal'}}", exporter.getEdgeDataSet(false));
    }

    @Test
    public void testGetEdgeDataSetWithArrowsAndLabel() {
        g.setEdgeWeight(g.getEdge(new Vertex("A"), new Vertex("C")), 7.0);
        exporter = new GraphVisJSExporterImpl(g);
        assertEquals("{from: 'A', to: 'C', label: '7', font: {align: 'horizontal'}, arrows: 'to'}", exporter.getEdgeDataSet(true));
    }
}
