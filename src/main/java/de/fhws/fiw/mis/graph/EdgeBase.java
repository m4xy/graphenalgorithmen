package de.fhws.fiw.mis.graph;

import org.jgrapht.WeightedGraph;

import java.io.Serializable;

/**
 * Created by maxarndt on 18.04.17.
 */
public class EdgeBase implements Edge, Cloneable, Serializable {
    private Vertex source;
    private Vertex target;
    private int weight;

    public EdgeBase() {

    }
    public EdgeBase(Vertex source, Vertex target) {
        this.source = source;
        this.target = target;
    }
    public EdgeBase(Vertex source, Vertex target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public Vertex getSource() {
        return source;
    }
    public void setSource(Vertex source) {
        this.source = source;
    }
    public Vertex getTarget() {
        return target;
    }
    public void setTarget(Vertex target) {
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
