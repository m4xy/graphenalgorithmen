package de.fhws.fiw.mis.graph.color;


import de.fhws.fiw.mis.graph.UndirectedBaseGraph;
import de.fhws.fiw.mis.graph.VertexBase;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void testColorEdges() {
        ColorAlgorithm.colorEdges(g);
        assertEquals(HtmlColor.values()[0], g.getEdges("A", "B").stream().findFirst().get().getColor().getColor());
        assertEquals(HtmlColor.values()[1], g.getEdges("A", "C").stream().findFirst().get().getColor().getColor());
        assertEquals(HtmlColor.values()[2], g.getEdges("A", "D").stream().findFirst().get().getColor().getColor());
        assertEquals(HtmlColor.values()[2], g.getEdges("B", "C").stream().findFirst().get().getColor().getColor());
        assertEquals(HtmlColor.values()[1], g.getEdges("B", "D").stream().findFirst().get().getColor().getColor());
        assertEquals(HtmlColor.values()[0], g.getEdges("C", "D").stream().findFirst().get().getColor().getColor());
    }
}
