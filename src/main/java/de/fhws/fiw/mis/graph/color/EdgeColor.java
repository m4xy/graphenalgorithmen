package de.fhws.fiw.mis.graph.color;

/**
 * Created by maxarndt on 27.04.17.
 */
public class EdgeColor {
    private String color;

    public EdgeColor() {
        this.color = HtmlColors.BLACK;
    }
    public EdgeColor(String color) {
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
        return "color: '" + this.color + "'";
    }
}
