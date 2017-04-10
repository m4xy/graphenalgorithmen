package de.fhws.fiw.mis.webapp.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * Created by maxarndt on 09.04.17.
 */
@ManagedBean(name = "indexBean", eager = true)
@RequestScoped
public class IndexBean {

    public List<String> getFiles() {
        List<String> files = new LinkedList<>();

        try {
            String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            Files.walk(Paths.get(path + "graphs")).forEach(filePath -> {
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
}
