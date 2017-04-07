package de.fhws.fiw.mis.graph;

/**
 * Created by maxarndt on 07.04.17.
 */
public class Vertex {
    private String name;
    private int data;
    private String color;

    public Vertex(String name) {
        this(name, 0);
    }
    public Vertex(String name, int data) {
        this.name = name;
        this.data = data;
    }
    public Vertex(String name, int data, String color) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

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
