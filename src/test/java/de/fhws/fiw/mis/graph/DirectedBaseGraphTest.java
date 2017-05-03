package de.fhws.fiw.mis.graph;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Created by maxarndt on 23.04.17.
 */
public class DirectedBaseGraphTest {
    DirectedBaseGraph g;

    @Before
    public void setUp() {
        g = new DirectedBaseGraph();
        g.addVertex(new VertexBase("A"));
        g.addVertex(new VertexBase("B"));
        g.addVertex(new VertexBase("C"));

        g.addEdge("A", "B");
        g.addEdge("A", "C");
    }

    @Test
    public void testGetMaxFlow() {
        assertEquals(1, g.getMaxFlow(new VertexBase("A"), new VertexBase("B")));
    }
    @Test
    public void testGetMaxFlow2() {
        g.addEdge("A", "B", 8);
        assertEquals(9, g.getMaxFlow(new VertexBase("A"), new VertexBase("B")));
    }
    @Test
    public void testGetMaxFlow3() {
        g.addVertex(new VertexBase("D"));
        g.addVertex(new VertexBase("E"));
        g.addEdge("A", "D", 8);
        g.addEdge("B", "E");
        g.addEdge("B", "C");
        g.addEdge("B", "D");
        assertEquals(9, g.getMaxFlow(new VertexBase("A"), new VertexBase("D")));

    }

    @Test
    public void testGetPath() {
        assertEquals(new LinkedList<>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("B")))), g.getPath(new VertexBase("A"), new VertexBase("B")));
    }
    @Test
    public void testGetPath2() {
        g.addVertex(new VertexBase("D"));
        g.addEdge("B", "C");
        g.addEdge("C", "B");
        g.addEdge("D", "A");
        g.addEdge("C", "D");
        LinkedList<Edge> expected = new LinkedList<>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("B")), new EdgeBase(new VertexBase("B"), new VertexBase("C")), new EdgeBase(new VertexBase("C"), new VertexBase("D"))));
        assertEquals(expected, g.getPath(new VertexBase("A"), new VertexBase("D")));
    }

    @Test
    public void testHasCycle() {
        assertFalse(g.hasCycle());
    }
    @Test
    public void testHasCycle2() {
        g.addEdge("B", "C");
        g.addEdge("C", "A");
        assertTrue(g.hasCycle());
    }

    @Test
    public void testGetIncomingEdgesOf() {
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("C")))), g.getIncomingEdgesOf(new VertexBase("C")));
    }
    @Test
    public void testGetIncomingEdgesOf2() {
        g.addEdge("C", "B");
        g.addEdge("B", "C", 9);
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("C")), new EdgeBase(new VertexBase("B"), new VertexBase("C"), 9))), g.getIncomingEdgesOf(new VertexBase("C")));
    }

    @Test
    public void testGetOutgoingEdgesOf() {
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("C")), new EdgeBase(new VertexBase("A"), new VertexBase("B")))), g.getOutgoingEdgesOf(new VertexBase("A")));
    }
    @Test
    public void testGetOutgoingEdgesOf2() {
        g.addEdge("C", "B");
        g.addEdge("B", "A");
        g.addEdge("B", "C", 9);
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("B"), new VertexBase("A")), new EdgeBase(new VertexBase("B"), new VertexBase("C"), 9))), g.getOutgoingEdgesOf(new VertexBase("B")));
    }

    @Test
    public void testGetInDegreeOf() {
        assertEquals(1, g.getInDegreeOf(new VertexBase("C")));
    }
    @Test
    public void testGetInDegreeOf2() {
        assertEquals(0, g.getInDegreeOf(new VertexBase("A")));
    }
    @Test
    public void testGetInDegreeOf3() {
        g.addEdge("C", "B");
        assertEquals(2, g.getInDegreeOf(new VertexBase("B")));
    }

    @Test
    public void testGetOutDegreeOf() {
        assertEquals(0, g.getOutDegreeOf(new VertexBase("C")));
    }
    @Test
    public void testGetOutDegreeOf2() {
        assertEquals(2, g.getOutDegreeOf(new VertexBase("A")));
    }

    @Test
    public void testAddEdgeSelf() {
        g.addEdge("B", "B");
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("B"), new VertexBase("B")), new EdgeBase(new VertexBase("A"), new VertexBase("B")), new EdgeBase(new VertexBase("A"), new VertexBase("C")))), g.getAllEdges());
    }

    @Test
    public void testGetEdgesSingleEdge() {
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("B")))), g.getEdges(new VertexBase("A"), new VertexBase("B")));
    }
    @Test
    public void testGetEdgesSingleEdgeFalse() {
        assertEquals(new HashSet<Edge>(), g.getEdges(new VertexBase("B"), new VertexBase("A")));
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
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("B"), new VertexBase("A"), 7), new EdgeBase(new VertexBase("B"), new VertexBase("A"), 3))), edges);
    }

    @Test
    public void testContainsEdge() {
        assertTrue(g.containsEdge(new VertexBase("A"), new VertexBase("C")));
    }
    @Test
    public void testContainsEdge2() {
        assertTrue(g.containsEdge(new EdgeBase(new VertexBase("A"), new VertexBase("C"))));
    }
    @Test
    public void testContainsEdgeFail() {
        assertFalse(g.containsEdge(new VertexBase("B"), new VertexBase("A")));
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
        assertEquals(new HashSet<Edge>(Arrays.asList(new EdgeBase(new VertexBase("A"), new VertexBase("C")), new EdgeBase(new VertexBase("B"), new VertexBase("A"), 5))), g.getAllEdges());
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
}
