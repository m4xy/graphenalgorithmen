package de.fhws.fiw.mis.graph.io.dotconverter;

/**
 * Created by maxarndt on 25.03.17.
 */
public interface DotConverter {
    void convertToPNG(String dotFilePath);
    void convertToPS(String dotFilePath);
}
