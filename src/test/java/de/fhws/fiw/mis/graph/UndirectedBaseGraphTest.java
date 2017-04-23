package de.fhws.fiw.mis.graph;


import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by maxarndt on 23.04.17.
 */
public class UndirectedBaseGraphTest {
    UndirectedBaseGraph g;

    @Before
    public void setUp() {
        g = new UndirectedBaseGraph();
        g.addVertex(new VertexBase("A"));
        g.addVertex(new VertexBase("B"));
        g.addVertex(new VertexBase("C"));

        g.addEdge(new VertexBase("A"), new VertexBase("B"));
        g.addEdge(new VertexBase("A"), new VertexBase("C"));
    }

    @Test
    public void testGetEdgesSingleEdge() {
        Set<Edge> edges = g.getEdges(new VertexBase("A"), new VertexBase("B"));
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("B")))), edges);
    }
    @Test
    public void testGetEdgesSingleEdgeSwapped() {
        Set<Edge> edges = g.getEdges(new VertexBase("B"), new VertexBase("A"));
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("B")))), edges);
    }

    @Test
    public void testGetEdgesMultipleEdges() {
        g.addEdge(new VertexBase("A"), new VertexBase("B"), 3);
        Set<Edge> edges = g.getEdges(new VertexBase("A"), new VertexBase("B"));
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("B")), new EdgeBase(new VertexBase("A"), new VertexBase("B"), 3))), edges);
    }
    @Test
    public void testGetEdgesMultipleEdges2() {
        g.addEdge(new VertexBase("B"), new VertexBase("A"), 3);
        g.addEdge(new VertexBase("B"), new VertexBase("A"), 7);
        Set<Edge> edges = g.getEdges(new VertexBase("B"), new VertexBase("A"));
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("B")), new EdgeBase(new VertexBase("B"), new VertexBase("A"), 7), new EdgeBase(new VertexBase("B"), new VertexBase("A"), 3))), edges);
    }

    @Test
    public void testContainsEdge() {
        assertTrue(g.containsEdge(new VertexBase("A"), new VertexBase("C")));
    }
    @Test
    public void testContainsEdgeSwapped() {
        assertTrue(g.containsEdge(new VertexBase("B"), new VertexBase("A")));
    }
    @Test
    public void testContainsEdgeFail() {
        assertFalse(g.containsEdge(new VertexBase("B"), new VertexBase("E")));
    }
}
