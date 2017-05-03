package de.fhws.fiw.mis.webapp.bean;

import de.fhws.fiw.mis.graph.AbstractGraph;
import de.fhws.fiw.mis.graph.VertexBase;
import de.fhws.fiw.mis.graph.color.ColorAlgorithm;
import de.fhws.fiw.mis.graph.io.exporter.GraphVisJSExporterImpl;
import de.fhws.fiw.mis.graph.io.importer.GraphImporter;
import de.fhws.fiw.mis.graph.io.importer.GraphImporterImpl;
import de.fhws.fiw.mis.webapp.ContextualStyle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by maxarndt on 09.04.17.
 */
@ManagedBean(name = "indexBean", eager = true)
@ViewScoped
public class IndexBean {
    private ExternalContext externalContext;
    private String webAppRealPath;
    private AbstractGraph graph;
    private GraphVisJSExporterImpl visExporter;

    @ManagedProperty(value="#{sessionBean}")
    private SessionBean sessionBean;

    private String graphFileName;
    private String statusMessage;
    private String statusType;
    private String startVertexName;
    private String javaScript;

    public SessionBean getSessionBean() {
        return sessionBean;
    }
    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

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
    public String getStatusType() {
        return statusType;
    }
    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }
    public String getStartVertexName() {
        return startVertexName;
    }
    public void setStartVertexName(String startVertexName) {
        this.startVertexName = startVertexName;
    }
    public String getJavaScript() {
        return this.javaScript;
    }
    public void setJavaScript(String javaScript) {
        this.javaScript = javaScript;
    }
    public boolean hasJavaScript() {
        return javaScript != null;
    }

    public IndexBean() {
        this.externalContext = FacesContext.getCurrentInstance().getExternalContext();
        this.webAppRealPath = this.externalContext.getRealPath("/");

        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
        String fileNameParam = parameterMap.get("fileName");
        if(fileNameParam == null)
            fileNameParam = "Dijkstra";
        setGraphFileName(fileNameParam);
    }
    @PostConstruct
    public void init() {
        GraphImporter importer = new GraphImporterImpl(webAppRealPath + "graphs");
        graph = sessionBean.getUndirectedGraph() ? importer.importGraph(getGraphFileName()) : importer.importDirectedGraph(getGraphFileName());
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
        return visExporter.getEdgeDataSet(!sessionBean.getUndirectedGraph());
    }

    public void calculateEulerianCircuit() {
        setStatus("Eulerkreis: " + graph.hasEulerianCircuit(), ContextualStyle.SUCCESS);
    }
    public void calculateEulerianPath() {
        setStatus("Eulerpfad: " + graph.hasEulerianPath(), ContextualStyle.SUCCESS);
    }
    public void calculateDepthSearch() {
        if(startVertexName.isEmpty() || startVertexName == "") {
            setStatus("Wähle einen Knoten aus!", ContextualStyle.DANGER);
        }
        else {
            setStatus("Tiefensuche: " +
                    graph.depthFirstSearch(findVertex(startVertexName)).stream()
                            .map(v -> v.getName())
                            .collect(Collectors.joining(", "))
            , ContextualStyle.SUCCESS);
        }
    }
    public void calculateBreadthSearch() {
        if(startVertexName.isEmpty() || startVertexName == "") {
            setStatus("Wähle einen Knoten aus!", ContextualStyle.DANGER);
        }
        else {
            setStatus("Breitensuche: " +
                            graph.breadthFirstSearch(findVertex(startVertexName)).stream()
                                    .map(v -> v.getName())
                                    .collect(Collectors.joining(", "))
                    , ContextualStyle.SUCCESS);
        }
    }
    public void calculateCircuit() {
        setStatus("Kreis: " + graph.hasCycle(), ContextualStyle.SUCCESS);
    }
    public void calculateMaxFlow() {
        setStatus("Maximaler Fluss: " + graph.getMaxFlow(findVertex("S"), findVertex("T")), ContextualStyle.SUCCESS);
    }

    public void greedyCol() {
        ColorAlgorithm.greedyCol(graph);
        updateGraph();
    }

    private void updateGraph() {
        setJavaScript("network.setData({nodes: new vis.DataSet([" + visExporter.getNodeDataSet() + "]), edges: new vis.DataSet([" + visExporter.getEdgeDataSet(!sessionBean.getUndirectedGraph()) + "])});");
    }

    private void setStatus(String message, ContextualStyle style) {
        setStatusType(style.name().toLowerCase());
        setStatusMessage(message);
    }
    private VertexBase findVertex(String name) {
        return (VertexBase)graph.getAllVertices().stream().filter(v -> v.getName().equals(name)).findFirst().get();
    }
}
