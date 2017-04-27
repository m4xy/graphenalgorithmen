package de.fhws.fiw.mis.graph.io.exporter;


import static org.junit.Assert.*;

import de.fhws.fiw.mis.graph.UndirectedBaseGraph;
import de.fhws.fiw.mis.graph.VertexBase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by maxarndt on 11.04.17.
 */
public class GraphVisJSExprterTest {
    GraphVisJSExporter exporter;
    UndirectedBaseGraph g;

    @Before
    public void setUp() {
        g = new UndirectedBaseGraph();
        g.addVertex(new VertexBase("A"));
        g.addVertex(new VertexBase("B"));
        g.addVertex(new VertexBase("C"));

        g.addEdge(new VertexBase("A"), new VertexBase("C"));
    }

    @Test
    public void testGetNodeDataSet() {
        exporter = new GraphVisJSExporterImpl(g);
        assertEquals("{id: 'A', label: 'A', color: {background: 'white', border: 'black'}},{id: 'B', label: 'B', color: {background: 'white', border: 'black'}},{id: 'C', label: 'C', color: {background: 'white', border: 'black'}}", exporter.getNodeDataSet());
    }

    @Test
    public void testGetEdgeDataSet() {
        exporter = new GraphVisJSExporterImpl(g);
        assertEquals("{from: 'A', to: 'C', color: 'black'}", exporter.getEdgeDataSet(false));
    }

    @Test
    public void testGetEdgeDataSetWithArrows() {
        exporter = new GraphVisJSExporterImpl(g);
        assertEquals("{from: 'A', to: 'C', color: 'black', arrows: 'to'}", exporter.getEdgeDataSet(true));
    }

    @Test
    public void testGetEdgeDataSetWithLabel() {
        g.getEdges(new VertexBase("A"), new VertexBase("C")).stream().findFirst().get().setWeight(7);
        exporter = new GraphVisJSExporterImpl(g);
        assertEquals("{from: 'A', to: 'C', color: 'black', label: '7', font: {align: 'horizontal'}}", exporter.getEdgeDataSet(false));
    }

    @Test
    public void testGetEdgeDataSetWithArrowsAndLabel() {
        g.getEdges(new VertexBase("A"), new VertexBase("C")).stream().findFirst().get().setWeight(7);
        exporter = new GraphVisJSExporterImpl(g);
        assertEquals("{from: 'A', to: 'C', color: 'black', label: '7', font: {align: 'horizontal'}, arrows: 'to'}", exporter.getEdgeDataSet(true));
    }
}
