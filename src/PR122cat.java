import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PR122cat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario la ruta del archivo
        System.out.print("Introduce la ruta del archivo de texto: ");
        String filePath = scanner.nextLine();
        scanner.close();

        File file = new File(filePath);

        // Verificar si el archivo existe
        if (!file.exists()) {
            System.out.println("El archivo no existe.");
            return;
        }

        // Verificar si la ruta corresponde a un archivo de texto
        if (file.isFile()) {
            try {
                // Leer y mostrar el contenido del archivo
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                reader.close();
            } catch (IOException e) {
                System.out.println("Error al leer el archivo.");
            }
        } else {
            // La ruta no corresponde a un archivo, sino a una carpeta u otro tipo de archivo
            System.out.println("El path no corresponde a un archivo, sino a una carpeta u otro tipo de archivo.");
        }
    }
}
