package de.fhws.fiw.mis.graph.color;

/**
 * Created by maxarndt on 27.04.17.
 */
public class VertexColor {
    private HtmlColor color;

    public VertexColor() {
        this.color = HtmlColor.WHITE;
    }
    public VertexColor(HtmlColor color) {
        this.color = color;
    }

    public HtmlColor getColor() { return this.color; }
    public void setColor(HtmlColor color) {
        this.color = color;
    }

    public String getColorAsString() {
        return color.name().toLowerCase();
    }

    @Override
    public String toString() {
        return "color: {background: '" + getColorAsString() + "', border: '" + HtmlColor.BLACK.name().toLowerCase() +"'}";
    }
}
