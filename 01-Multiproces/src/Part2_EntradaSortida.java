import java.io.*;
import java.nio.charset.StandardCharsets;

public class Part2_EntradaSortida {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Sistema operatiu detectat: " + SO.nomSO());

        ProcessBuilder pb = new ProcessBuilder(SO.ordenar());
        pb.redirectErrorStream(true);
        Process process = pb.start();

        PrintWriter writer = new PrintWriter(
            new OutputStreamWriter(process.getOutputStream(), StandardCharsets.UTF_8), true);

        String[] fruites = {"plàtan", "poma", "albergínia", "cireres", "maduixa"};

        System.out.println("Enviem al procés 'sort':");
        for (String fruita : fruites) {
            System.out.println("  -> " + fruita);
            writer.println(fruita);
        }
        writer.close();

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));

        System.out.println("\nResposta del procés 'sort' (ordenat):");
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("  <- " + line);
        }

        int code = process.waitFor();
        System.out.println("\nCodi de retorn: " + code);
    }
}