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
    public EdgeColor(EdgeColor clone) {
        this.color = clone.getColor();
    }

    public HtmlColor getColor() {
        return this.color;
    }
    public void setColor(HtmlColor color) {
        this.color = color;
    }

    public String getColorAsString() {
        return color.name().toLowerCase();
    }

    @Override
    public String toString() {
        return "color: '" + getColorAsString() + "'";
    }
}
