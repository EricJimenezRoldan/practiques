import java.io.File;
import java.io.IOException;

public class PR121Files {
    public static void main(String[] args) {
        // Nombre de la carpeta y archivos
        String folderName = "myFiles";
        String file1Name = "file1.txt";
        String file2Name = "file2.txt";
        String renamedFileName = "renamedFile.txt";

        // Crear una carpeta llamada "myFiles"
        File folder = new File(folderName);
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Se ha creado la carpeta 'myFiles'.");
            } else {
                System.out.println("No se pudo crear la carpeta 'myFiles'.");
                return;
            }
        }

        // Crear dos archivos dentro de la carpeta "myFiles"
        File file1 = new File(folder, file1Name);
        File file2 = new File(folder, file2Name);

        try {
            if (file1.createNewFile() && file2.createNewFile()) {
                System.out.println("Se han creado los archivos 'file1.txt' y 'file2.txt' dentro de 'myFiles'.");
            } else {
                System.out.println("No se pudieron crear los archivos.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Renombrar el archivo "file2.txt" a "renamedFile.txt"
        if (file2.renameTo(new File(folder, renamedFileName))) {
            System.out.println("Se ha renombrado 'file2.txt' a 'renamedFile.txt'.");
        } else {
            System.out.println("No se pudo renombrar el archivo.");
        }

        // Listar los archivos en la carpeta "myFiles"
        File[] filesInFolder = folder.listFiles();
        if (filesInFolder != null && filesInFolder.length > 0) {
            System.out.println("Los archivos de la carpeta son:");
            for (File file : filesInFolder) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("La carpeta 'myFiles' está vacía.");
        }

        // Eliminar el archivo "file1.txt"
        if (file1.delete()) {
            System.out.println("Se ha eliminado 'file1.txt'.");
        } else {
            System.out.println("No se pudo eliminar el archivo 'file1.txt'.");
        }

        // Listar los archivos en la carpeta "myFiles" nuevamente
        filesInFolder = folder.listFiles();
        if (filesInFolder != null && filesInFolder.length > 0) {
            System.out.println("Los archivos de la carpeta son:");
            for (File file : filesInFolder) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("La carpeta 'myFiles' está vacía.");
        }
    }
}
