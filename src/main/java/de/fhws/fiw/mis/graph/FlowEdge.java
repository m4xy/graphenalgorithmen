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
    public FlowEdge(Vertex source, Vertex target, int weight, int currentFlow) {
        super(source, target, weight);
        this.currentFlow = currentFlow;
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
}
