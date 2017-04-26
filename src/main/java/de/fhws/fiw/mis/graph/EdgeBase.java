package de.fhws.fiw.mis.graph;

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
        this(source, target, 1);

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
    public void incrementWeight(int delta) {
        this.weight += delta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EdgeBase edgeBase = (EdgeBase) o;

        if (weight != edgeBase.weight) return false;
        if (!source.equals(edgeBase.source)) return false;
        return target.equals(edgeBase.target);
    }

    @Override
    public int hashCode() {
        int result = source.hashCode();
        result = 31 * result + target.hashCode();
        result = 31 * result + weight;
        return result;
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
        return "(" + source + " : " + target + " {" + weight + "})";
    }
}
