package de.fhws.fiw.mis.graph;

import org.jgrapht.WeightedGraph;

import java.io.Serializable;

/**
 * Created by maxarndt on 18.04.17.
 */
public class EdgeBase implements Edge, Cloneable, Serializable {
    Object source;
    Object target;
    int weight = new Double(WeightedGraph.DEFAULT_EDGE_WEIGHT).intValue();

    public EdgeBase() {

    }
    public EdgeBase(Object source, Object target) {
        this.source = source;
        this.target = target;
    }
    public EdgeBase(Object source, Object target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public Object getSource() {
        return source;
    }
    public void setSource(Object source) {
        this.source = source;
    }
    public Object getTarget() {
        return target;
    }
    public void setTarget(Object target) {
        this.target = target;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public Object clone()
    {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // shouldn't happen as we are Cloneable
            throw new InternalError();
        }
    }
    @Override
    public String toString()
    {
        return "(" + source + " : " + target + "{" + weight + "})";
    }
}
