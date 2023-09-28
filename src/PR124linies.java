import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class PR124linies {
    public static void main(String[] args) {
        // Ruta del archivo
        String filePath = "numeros.txt";

        try {
            // Abrir el archivo para escritura
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // Generar y escribir 10 números aleatorios en líneas separadas
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                int numeroAleatorio = random.nextInt(100); // Cambia 100 al rango deseado
                printWriter.println(numeroAleatorio);
            }

            // Cerrar el archivo
            printWriter.close();

            System.out.println("Se han generado y escrito los números en el archivo 'numeros.txt' correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
            e.printStackTrace();
        }
    }
}
