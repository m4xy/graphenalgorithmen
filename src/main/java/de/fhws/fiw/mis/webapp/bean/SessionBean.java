package de.fhws.fiw.mis.webapp.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by maxarndt on 17.04.17.
 */
@ManagedBean(name = "sessionBean", eager = true)
@SessionScoped
public class SessionBean {
    private Boolean undirectedGraph;

    public Boolean getUndirectedGraph() {
        return undirectedGraph;
    }
    public void setUndirectedGraph(Boolean undirectedGraph) {
        this.undirectedGraph = undirectedGraph;
    }

    public SessionBean() {
        undirectedGraph = true;
    }
}
