package de.fhws.fiw.mis.graph.color;


import de.fhws.fiw.mis.graph.UndirectedBaseGraph;
import de.fhws.fiw.mis.graph.VertexBase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by maxarndt on 05.05.17.
 */
public class ColorAlgorithmTest {
    UndirectedBaseGraph g;

    @Before
    public void setUp() {
        g = new UndirectedBaseGraph();
        g.addVertex(new VertexBase("A"));
        g.addVertex(new VertexBase("B"));
        g.addVertex(new VertexBase("C"));
        g.addVertex(new VertexBase("D"));

        g.addEdge("A", "B");
        g.addEdge("A", "C");
        g.addEdge("A", "D");
        g.addEdge("B", "C");
        g.addEdge("B", "D");
        g.addEdge("C", "D");
    }
    public void setUp5VerticeGraph() {
        g.addVertex(new VertexBase("E"));
        g.addEdge("A", "E");
        g.addEdge("B", "E");
        g.addEdge("C", "E");
        g.addEdge("D", "E");
    }
    public void setUp6VerticeGraph() {
        setUp5VerticeGraph();
        g.addVertex(new VertexBase("F"));
        g.addEdge("A", "F");
        g.addEdge("B", "F");
        g.addEdge("C", "F");
        g.addEdge("D", "F");
        g.addEdge("E", "F");
    }

    @Test
    public void testColorEdgesAB() {
        ColorAlgorithm.colorEdges(g);
        assertEquals(HtmlColor.values()[2], g.getEdges("A", "B").stream().findFirst().get().getColor().getColor());
    }

    @Test
    public void testColorEdgesAC() {
        ColorAlgorithm.colorEdges(g);
        assertEquals(HtmlColor.values()[0], g.getEdges("A", "C").stream().findFirst().get().getColor().getColor());
    }

    @Test
    public void testColorEdgesAD() {
        ColorAlgorithm.colorEdges(g);
        assertEquals(HtmlColor.values()[1], g.getEdges("A", "D").stream().findFirst().get().getColor().getColor());
    }

    @Test
    public void testColorEdgesBC() {
        ColorAlgorithm.colorEdges(g);
        assertEquals(HtmlColor.values()[1], g.getEdges("B", "C").stream().findFirst().get().getColor().getColor());
    }

    @Test
    public void testColorEdgesBD() {
        ColorAlgorithm.colorEdges(g);
        assertEquals(HtmlColor.values()[0], g.getEdges("B", "D").stream().findFirst().get().getColor().getColor());
    }

    @Test
    public void testColorEdgesCD() {
        ColorAlgorithm.colorEdges(g);
        assertEquals(HtmlColor.values()[2], g.getEdges("C", "D").stream().findFirst().get().getColor().getColor());
    }

    @Test
    public void testColorEdges5er() {
        setUp5VerticeGraph();
        ColorAlgorithm.colorEdges(g);
        assertFalse(true);
    }

    @Test
    public void testColorEdges6er() {
        setUp6VerticeGraph();
        ColorAlgorithm.colorEdges(g);
        assertFalse(true);
    }

    @Test
    public void testIsAnyEdgeSpecificColored() {
        g.getEdges("A", "B").stream().findFirst().get().setColor(HtmlColor.BLUE);
        assertTrue(ColorAlgorithm.isAnyEdgeSpecificColored(new ArrayList<>(g.getAllEdges()), 4));
    }

    @Test
    public void testGetFirstMissingColor() {
        g.getEdges("A", "B").stream().findFirst().get().setColor(HtmlColor.BLUE);
        assertEquals(HtmlColor.AQUA, ColorAlgorithm.getFirstMissingColor(g.getAllEdges()));
    }
    @Test
    public void testGetFirstMissingColor2() {
        g.getEdges("A", "B").stream().findFirst().get().setColor(HtmlColor.BLUE);
        g.getEdges("A", "C").stream().findFirst().get().setColor(HtmlColor.AQUA);
        assertEquals(HtmlColor.ORANGE, ColorAlgorithm.getFirstMissingColor(g.getAllEdges()));
    }
}
