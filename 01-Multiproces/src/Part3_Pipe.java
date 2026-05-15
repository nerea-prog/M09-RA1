import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Part3_Pipe {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Sistema operatiu detectat: " + SO.nomSO());
        System.out.println("=== Fitxers .java trobats ===");

        ProcessBuilder pb1 = new ProcessBuilder(SO.llistarFitxers());
        File srcDir = new File("src");
        // Si no troba, llista en on estiguis ubicat
        if (!srcDir.isDirectory()) {
            srcDir = new File(".");
        }
        pb1.directory(srcDir);

        ProcessBuilder pb2 = new ProcessBuilder(SO.filtrar(".java"));

        List<Process> pipeline = ProcessBuilder.startPipeline(Arrays.asList(pb1, pb2));

        Process darrerProces = pipeline.get(pipeline.size() - 1);

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(darrerProces.getInputStream(), StandardCharsets.UTF_8));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        for (Process p : pipeline) {
            p.waitFor();
        }

        System.out.println("\nPipeline completat.");
    }
}