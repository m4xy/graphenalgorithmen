package de.fhws.fiw.mis.webapp.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by maxarndt on 10.04.17.
 */
@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        String bla = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        this.name = name;
    }
}
