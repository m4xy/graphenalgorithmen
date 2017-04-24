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

    @Test
    public void testContainsVertex() {
        assertTrue(g.containsVertex(new VertexBase("A")));
    }
    @Test
    public void testContainsVertexFalse() {
        assertFalse(g.containsVertex(new VertexBase("a")));
    }

    @Test
    public void testGetEdgeSet() {
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("B")), new EdgeBase(new VertexBase("A"), new VertexBase("C")))), g.getEdgeSet());
    }
    @Test
    public void testGetEdgeSet2() {
        g.addEdge(new VertexBase("A"), new VertexBase("B"), 3);
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("B")), new EdgeBase(new VertexBase("A"), new VertexBase("B"), 3) , new EdgeBase(new VertexBase("A"), new VertexBase("C")))), g.getEdgeSet());
    }

    @Test
    public void testGetEdgesOf() {
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("C")))), g.getEdgesOf(new VertexBase("C")));
    }

    @Test
    public void testRemoveEdge() {
        g.removeEdge(new EdgeBase(new VertexBase("A"), new VertexBase("B")));
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("C")))), g.getEdgeSet());
    }

    @Test
    public void testRemoveEdges() {
        g.addEdge(new VertexBase("A"), new VertexBase("B"), 3);
        g.removeEdges(new VertexBase("A"), new VertexBase("B"));
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("C")))), g.getEdgeSet());
    }
    @Test
    public void testRemoveEdgesFalse() {
        g.addEdge(new VertexBase("A"), new VertexBase("B"), 3);
        g.addEdge(new VertexBase("B"), new VertexBase("A"), 5);
        g.removeEdges(new VertexBase("A"), new VertexBase("B"));
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("C")))), g.getEdgeSet());
    }

    @Test
    public void testRemoveVertex() {
        g.removeVertex(new VertexBase("A"));
        assertEquals(new HashSet<Vertex>(Arrays.asList(new VertexBase("C"), new VertexBase("B"))), g.getVertexSet());
    }

    @Test
    public void testGetVertexSet() {
        assertEquals(new HashSet<Vertex>(Arrays.asList(new VertexBase("A"), new VertexBase("B"), new VertexBase("C"))), g.getVertexSet());
    }
    @Test
    public void testGetVertexSet2() {
        g.addVertex(new VertexBase("D", 4));
        assertEquals(new HashSet<Vertex>(Arrays.asList(new VertexBase("A"), new VertexBase("B"), new VertexBase("C"), new VertexBase("D", 4))), g.getVertexSet());
    }
}
