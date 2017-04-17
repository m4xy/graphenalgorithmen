package de.fhws.fiw.mis.webapp.bean;

import de.fhws.fiw.mis.graph.UndirGraph;
import de.fhws.fiw.mis.graph.io.exporter.GraphVisJSExporterImpl;
import de.fhws.fiw.mis.graph.io.importer.GraphImporter;
import de.fhws.fiw.mis.graph.io.importer.GraphImporterImpl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * Created by maxarndt on 09.04.17.
 */
@ManagedBean(name = "indexBean", eager = true)
@RequestScoped
public class IndexBean {
    private String externalContext;
    private UndirGraph graph;
    private GraphVisJSExporterImpl visExporter;

    public IndexBean() {
        this.externalContext = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        GraphImporter importer = new GraphImporterImpl(externalContext + "graphs");
        graph = importer.importGraph("Baum.txt");
        visExporter = new GraphVisJSExporterImpl(graph);
    }

    public List<String> getFiles() {
        List<String> files = new LinkedList<>();

        try {
            Files.walk(Paths.get(externalContext + "graphs")).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    files.add(filePath.getFileName().toString().substring(0, filePath.getFileName().toString().length() -4));
                }
            });
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return files;
    }

    public String getVisNodeDataSet() {
        return visExporter.getNodeDataSet();
    }
    public String getVisEdgeDataSet() {
        return visExporter.getEdgeDataSet();
    }
}
