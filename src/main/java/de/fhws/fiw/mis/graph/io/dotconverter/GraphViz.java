package de.fhws.fiw.mis.graph.io.dotconverter;

import java.io.*;

/**
 * Created by maxarndt on 25.03.17.
 */
public class GraphViz implements DotConverter {
    @Override
    public void convertToPNG(String dotFilePath) {
        executeGraphVizCmd(dotFilePath, OutputFileType.PNG);
    }

    @Override
    public void convertToPS(String dotFilePath) {
        executeGraphVizCmd(dotFilePath, OutputFileType.PS);
    }

    public void executeGraphVizCmd(String dotFilePath, OutputFileType outFileType) {
        String command = "dot";
        try {
            File dotFile = getFile(dotFilePath);
            command = buildGraphVizCommand(dotFile.getCanonicalPath(), outFileType);
            String outStr = executeShell(command);

            System.out.println(parseCmdOutput(outStr));
            System.out.println(getFile(buildOutFilePath(dotFilePath, outFileType)).getCanonicalFile());
        }
        catch (IOException | InterruptedException e) {
            System.out.println("Error executing GrapViz command 'dot'. \nPlease verify that GraphViz is installed and '" + command + "' binary is executable from your command line. \nDo not forget to add its directory to your path environment.\n");
        }
    }
    public String buildOutFilePath(String dotFilePath, OutputFileType fileType) {
        return dotFilePath.replace(".dot", "." + fileType.name().toLowerCase());
    }
    public String buildGraphVizCommand(String dotFilePath, OutputFileType fileType) {
        return "dot -T" + fileType.name().toLowerCase() + " " + dotFilePath + " -o " + buildOutFilePath(dotFilePath, fileType);
    }
    public String parseCmdOutput(String cmdOutput) {
        if(cmdOutput.equals("")) {
            return ".dot File was converted successfully!";
        }
        return cmdOutput;
    }
    private File getFile(String path) throws FileNotFoundException {
        File f = new File(path);
        if(f.exists() && !f.isDirectory()) {
            return f;
        }
        throw new FileNotFoundException(".dot File does not exist! Submitted path: " + path);
    }
    private String executeShell(String command) throws IOException, InterruptedException {
        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            throw(e);
        }

        return output.toString();
    }
}
