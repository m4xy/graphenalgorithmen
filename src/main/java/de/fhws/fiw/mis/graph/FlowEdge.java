package de.fhws.fiw.mis.graph;

/**
 * Created by maxarndt on 18.04.17.
 */
public class FlowEdge extends Edge {
    private int currentFlow;

    public FlowEdge(Edge e) {
        super(e.getSource(), e.getTarget(), e.getWeight());
        this.currentFlow = 0;
    }

    @Override
    public String toString() {
        return "(" + source + " : " + target + "{" + currentFlow + "/" + weight + "})";
    }

    public int getCurrentFlow() {
        return currentFlow;
    }
    public void setCurrentFlow(int currentFlow) {
        this.currentFlow = currentFlow;
    }
    public int getMaxFlow() {
        return new Double(super.getWeight()).intValue();
    }
}
