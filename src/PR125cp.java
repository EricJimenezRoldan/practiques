import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Scanner;
import java.io.File;

public class PR125cp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario la ruta del archivo original
        System.out.print("Introduce la ruta del archivo original: ");
        String sourceFilePath = scanner.nextLine();

        // Solicitar al usuario la ruta de destino
        System.out.print("Introduce la ruta de destino: ");
        String destinationFilePath = scanner.nextLine();

        scanner.close();

        // Verificar si el archivo original existe
        File sourceFile = new File(sourceFilePath);
        if (!sourceFile.exists()) {
            System.out.println("El archivo original no se pudo encontrar.");
            return;
        }

        // Verificar si la ruta de destino es una carpeta existente
        File destinationFolder = new File(destinationFilePath);
        if (!destinationFolder.exists() || !destinationFolder.isDirectory()) {
            System.out.println("La ruta de destino no es una carpeta existente.");
            return;
        }

        // Concatenar el nombre del archivo original al destino si la ruta de destino es una carpeta
        if (destinationFolder.isDirectory()) {
            destinationFilePath = destinationFolder.getPath() + File.separator + sourceFile.getName();
        }

        try {
            // Abrir canales de entrada y salida para los archivos
            FileInputStream sourceFileInputStream = new FileInputStream(sourceFilePath);
            FileOutputStream destinationFileOutputStream = new FileOutputStream(destinationFilePath);

            FileChannel sourceChannel = sourceFileInputStream.getChannel();
            FileChannel destinationChannel = destinationFileOutputStream.getChannel();

            // Copiar el contenido del archivo de origen al archivo de destino
            destinationChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

            // Cerrar canales y archivos
            sourceFileInputStream.close();
            destinationFileOutputStream.close();
            sourceChannel.close();
            destinationChannel.close();

            System.out.println("Se ha copiado el archivo exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al copiar el archivo.");
            e.printStackTrace();
        }
    }
}
