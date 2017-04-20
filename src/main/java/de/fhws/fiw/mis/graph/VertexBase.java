package de.fhws.fiw.mis.graph;

/**
 * Created by maxarndt on 07.04.17.
 */
public class VertexBase implements Vertex {
    private String name;
    private int data;
    private String color;

    public VertexBase(String name) {
        this(name, 0);
    }
    public VertexBase(String name, int data) {
        this.name = name;
        this.data = data;
    }
    public VertexBase(String name, int data, String color) {
        this.name = name;
        this.data = data;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    @Override
    public boolean isInEdge(Edge e) {
        return this == e.getTarget();
    }

    @Override
    public boolean isOutEdge(Edge e) {
        return this == e.getSource();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VertexBase vertex = (VertexBase) o;

        return name.equals(vertex.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return data != 0 ? name + " (" + data + ")" : name;
    }
}
