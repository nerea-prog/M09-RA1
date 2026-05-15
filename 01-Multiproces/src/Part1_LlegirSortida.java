import java.io.*;
import java.nio.charset.StandardCharsets;

public class Part1_LlegirSortida {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Sistema operatiu detectat: " + SO.nomSO());
        System.out.println("=== Contingut del directori ===");

        ProcessBuilder pb = new ProcessBuilder(SO.llistarFitxers());

        pb.directory(new File("."));
        pb.redirectErrorStream(true);
        Process p = pb.start();

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int code = p.waitFor();
        System.out.println("El procés ha acabat amb codi: " + code);
    }
}