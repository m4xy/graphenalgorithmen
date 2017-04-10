package de.fhws.fiw.mis.webapp;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by maxarndt on 08.04.17.
 */
public class GraphLoader {
    public static List<String> getFiles() {
        List<String> files = new LinkedList<>();

        try {
            Files.walk(Paths.get("")).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    files.add(filePath.toString());
                }
            });
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return files;
    }
}
