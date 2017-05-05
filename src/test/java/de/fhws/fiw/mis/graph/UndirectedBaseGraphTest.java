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

        g.addEdge("A", "B");
        g.addEdge("A", "C");
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
        g.addEdge("A", "B", 3);
        Set<Edge> edges = g.getEdges(new VertexBase("A"), new VertexBase("B"));
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("B")), new EdgeBase(new VertexBase("A"), new VertexBase("B"), 3))), edges);
    }
    @Test
    public void testGetEdgesMultipleEdges2() {
        g.addEdge("B", "A", 3);
        g.addEdge("B", "A", 7);
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
    public void testGetAllEdges() {
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("B")), new EdgeBase(new VertexBase("A"), new VertexBase("C")))), g.getAllEdges());
    }
    @Test
    public void testGetAllEdges2() {
        g.addEdge("A", "B", 3);
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("B")), new EdgeBase(new VertexBase("A"), new VertexBase("B"), 3) , new EdgeBase(new VertexBase("A"), new VertexBase("C")))), g.getAllEdges());
    }

    @Test
    public void testGetEdgesOf() {
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("C")))), g.getEdgesOf(new VertexBase("C")));
    }

    @Test
    public void testRemoveEdge() {
        g.removeEdge(new EdgeBase(new VertexBase("A"), new VertexBase("B")));
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("C")))), g.getAllEdges());
    }

    @Test
    public void testRemoveEdges() {
        g.addEdge("A", "B", 3);
        g.removeEdges(new VertexBase("A"), new VertexBase("B"));
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("C")))), g.getAllEdges());
    }
    @Test
    public void testRemoveEdgesFalse() {
        g.addEdge("A", "B", 3);
        g.addEdge("B", "A", 5);
        g.removeEdges(new VertexBase("A"), new VertexBase("B"));
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("C")))), g.getAllEdges());
    }

    @Test
    public void testRemoveVertex() {
        g.removeVertex(new VertexBase("A"));
        assertEquals(new HashSet<Vertex>(Arrays.asList(new VertexBase("C"), new VertexBase("B"))), g.getAllVertices());
    }

    @Test
    public void testGetAllVertices() {
        assertEquals(new HashSet<Vertex>(Arrays.asList(new VertexBase("A"), new VertexBase("B"), new VertexBase("C"))), g.getAllVertices());
    }
    @Test
    public void testGetAllVertices2() {
        g.addVertex(new VertexBase("D", 4));
        assertEquals(new HashSet<Vertex>(Arrays.asList(new VertexBase("A"), new VertexBase("B"), new VertexBase("C"), new VertexBase("D", 4))), g.getAllVertices());
    }

    @Test
    public void testGetDegree() {
        assertEquals(2, g.getDegree());
    }
}
