package de.fhws.fiw.mis.graph.color;

/**
 * Created by maxarndt on 27.04.17.
 */
public class EdgeColor {
    private HtmlColor color;

    public EdgeColor() {
        this.color = HtmlColor.BLACK;
    }
    public EdgeColor(HtmlColor color) {
        this.color = color;
    }

    public String getColor() {
        return color.name().toLowerCase();
    }
    public void setColor(HtmlColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "color: '" + getColor() + "'";
    }
}
