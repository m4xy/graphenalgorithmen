package de.fhws.fiw.mis.graph.color;

/**
 * Created by maxarndt on 27.04.17.
 */
public class VertexColor {
    private String color;

    public VertexColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "color: {background: '" + this.color + "', border: '" + HtmlColors.BLACK +"'}";
    }
}
