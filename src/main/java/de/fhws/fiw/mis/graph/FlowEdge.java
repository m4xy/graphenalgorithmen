package de.fhws.fiw.mis.graph;

/**
 * Created by maxarndt on 18.04.17.
 */
public class FlowEdge extends EdgeBase {
    private int currentFlow;

    public FlowEdge(Edge e) {
        super(e.getSource(), e.getTarget(), e.getWeight());
        this.currentFlow = 0;
    }

    @Override
    public String toString() {
        return "(" + super.getSource().getName() + " : " + super.getTarget().getName() + " {" + currentFlow + "/" + super.getWeight() + "})";
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
