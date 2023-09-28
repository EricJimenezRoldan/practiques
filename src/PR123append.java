import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PR123append {
    public static void main(String[] args) {
        // Ruta del archivo
        String filePath = "frasesMatrix.txt";

        // Frases a escribir
        String[] frases = {
            "Yo sólo puedo mostrarte la puerta",
            "Tú eres quien la tiene que atravesar"
        };

        try {
            // Abrir el archivo para escritura (se agregará al final)
            FileWriter fileWriter = new FileWriter(filePath, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // Escribir las frases en el archivo
            for (String frase : frases) {
                printWriter.println(frase);
            }

            // Cerrar el archivo
            printWriter.close();

            System.out.println("Las frases se han agregado al archivo correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
            e.printStackTrace();
        }
    }
}
