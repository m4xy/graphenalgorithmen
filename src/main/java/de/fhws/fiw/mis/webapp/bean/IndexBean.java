package de.fhws.fiw.mis.webapp.bean;

import de.fhws.fiw.mis.graph.UndirGraph;
import de.fhws.fiw.mis.graph.io.exporter.GraphVisJSExporterImpl;
import de.fhws.fiw.mis.graph.io.importer.GraphImporter;
import de.fhws.fiw.mis.graph.io.importer.GraphImporterImpl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
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
    private ExternalContext externalContext;
    private String webAppRealPath;
    private UndirGraph graph;
    private GraphVisJSExporterImpl visExporter;

    private String graphFileName;
    private String statusMessage;

    public String getGraphFileName() {
        return graphFileName;
    }
    public void setGraphFileName(String graphFileName) {
        this.graphFileName = graphFileName.endsWith(".txt") ? graphFileName : graphFileName + ".txt";
    }
    public String getStatusMessage() {
        return statusMessage;
    }
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }


    public IndexBean() {
        this.externalContext = FacesContext.getCurrentInstance().getExternalContext();
        this.webAppRealPath = this.externalContext.getRealPath("/");
        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
        String fileNameParam = parameterMap.get("fileName");

        if(fileNameParam == null)
            fileNameParam = "Dijkstra";

        setGraphFileName(fileNameParam);
        GraphImporter importer = new GraphImporterImpl(webAppRealPath + "graphs");
        graph = importer.importGraph(getGraphFileName());
        visExporter = new GraphVisJSExporterImpl(graph);
    }


    public List<String> getFiles() {
        List<String> files = new LinkedList<>();

        try {
            Files.walk(Paths.get(webAppRealPath + "graphs")).forEach(filePath -> {
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

    public void calculateEulerkreis() {
        setStatusMessage("Eulerkreis ausgeführt.");
    }
}
