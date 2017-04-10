package de.fhws.fiw.mis.webapp.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by maxarndt on 09.04.17.
 */
@ManagedBean(name = "indexBean", eager = true)
@RequestScoped
public class IndexBean {
    private ServletContext context;


    public String getHelloWorld() {
        return "Hello World!";
    }
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
