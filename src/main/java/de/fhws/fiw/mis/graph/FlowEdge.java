package de.fhws.fiw.mis.graph;

/**
 * Created by maxarndt on 18.04.17.
 */
public class FlowEdge {
    private Edge edge;
    private int currentFlow;

    public FlowEdge(Edge e) {
        this.edge = e;
        this.currentFlow = 0;
    }

    public int getCurrentFlow() {
        return currentFlow;
    }
    public void setCurrentFlow(int currentFlow) {
        this.currentFlow = currentFlow;
    }
    public int getMaxFlow() {
        return edge.getEdgeWeight();
    }


}
