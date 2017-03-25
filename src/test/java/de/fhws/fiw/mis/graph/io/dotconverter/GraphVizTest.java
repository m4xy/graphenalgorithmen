package de.fhws.fiw.mis.graph.io.dotconverter;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by maxarndt on 25.03.17.
 */
public class GraphVizTest {
    @Test
    public void testBuildOutFilePath() {
        GraphViz converter = new GraphViz();
        assertEquals("/test/bla/foo/bar.png", converter.buildOutFilePath("/test/bla/foo/bar.dot", OutputFileType.PNG));
    }

    @Test
    public void testBuildGraphVizCommandPNG() {
        GraphViz converter = new GraphViz();
        assertEquals( "dot -Tpng /test/path/file.dot -o /test/path/file.png", converter.buildGraphVizCommand("/test/path/file.dot", OutputFileType.PNG));
    }

    @Test
    public void testBuildGraphVizCommandPS() {
        GraphViz converter = new GraphViz();
        assertEquals( "dot -Tps /test/path/file.dot -o /test/path/file.ps", converter.buildGraphVizCommand("/test/path/file.dot", OutputFileType.PS));
    }
}
