package de.fhws.fiw.mis.graph.color;

/**
 * Created by maxarndt on 27.04.17.
 */
public enum HtmlColor {
    AQUA(0), ORANGE(1), GREEN(2), RED(3), BLUE(4), PURPLE(5), WHITE(100), BLACK(Integer.MAX_VALUE);

    private int value;

    HtmlColor(int value) {
        this.value = value;
    }
}
