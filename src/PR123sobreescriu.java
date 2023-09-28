import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PR123sobreescriu {
    public static void main(String[] args) {
        // Ruta del archivo
        String filePath = "frasesMatrix.txt";

        // Frases a escribir
        String[] frases = {
            "Yo sólo puedo mostrarte la puerta",
            "Tú eres quien la tiene que atravesar"
        };

        try {
            // Abrir el archivo para escritura (se sobrescribirá)
            FileWriter fileWriter = new FileWriter(filePath, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // Escribir las frases en el archivo
            for (String frase : frases) {
                printWriter.println(frase);
            }

            // Cerrar el archivo
            printWriter.close();

            System.out.println("Las frases se han escrito en el archivo correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
            e.printStackTrace();
        }
    }
}
