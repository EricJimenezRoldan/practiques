import java.io.*;
import java.util.*;

public class PR133mainTreballadors {
    public static void main(String[] args) {
        // Ruta del archivo CSV
        String archivoCSV = "PR133treballadors.csv";

        // Crear el archivo CSV con los datos iniciales
        crearArchivoCSV(archivoCSV);

        // Leer los datos del archivo CSV
        List<String[]> datos = leerArchivoCSV(archivoCSV);

        // Mostrar la tabla actual
        System.out.println("Tabla de Treballadors:");
        for (String[] fila : datos) {
            for (String dato : fila) {
                System.out.print(dato + "\t");
            }
            System.out.println();
        }

        // Solicitar al usuario el identificador de trabajador y el dato a modificar
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el identificador del treballador: ");
        int identificador = Integer.parseInt(scanner.nextLine());

        System.out.print("Quina dada vols modificar (Nom, Cognom, Departament, Salari): ");
        String campo = scanner.nextLine();

        System.out.print("Introdueix el nou valor: ");
        String nuevoValor = scanner.nextLine();

        // Buscar y actualizar los datos en la lista
        boolean encontrado = false;
        for (String[] fila : datos) {
            int id = Integer.parseInt(fila[0]);
            if (id == identificador) {
                if (campo.equalsIgnoreCase("Nom")) {
                    fila[1] = nuevoValor;
                } else if (campo.equalsIgnoreCase("Cognom")) {
                    fila[2] = nuevoValor;
                } else if (campo.equalsIgnoreCase("Departament")) {
                    fila[3] = nuevoValor;
                } else if (campo.equalsIgnoreCase("Salari")) {
                    fila[4] = nuevoValor;
                }
                encontrado = true;
                break;
            }
        }

        // Si se encontró el trabajador y se actualizó el dato, guardar los cambios en el archivo CSV
        if (encontrado) {
            guardarArchivoCSV(archivoCSV, datos);
            System.out.println("Dades actualitzades amb èxit!");
        } else {
            System.out.println("Treballador no trobat. Les dades no s'han actualitzat.");
        }
    }

    // Método para crear el archivo CSV inicial con los datos proporcionados
    private static void crearArchivoCSV(String archivoCSV) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(archivoCSV));
            writer.println("Id,Nom,Cognom,Departament,Salari");
            writer.println("123,Nicolás,Rana,2,1000.00");
            writer.println("435,Xavi,Gil,2,1800.50");
            writer.println("876,Daniel,Ramos,6,700.30");
            writer.println("285,Pedro,Drake,4,2500.00");
            writer.println("224,Joan,Potter,6,1000.00");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para leer los datos desde el archivo CSV
    private static List<String[]> leerArchivoCSV(String archivoCSV) {
        List<String[]> datos = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivoCSV));
            String linea;
            br.readLine(); // Saltar la primera línea (encabezados)
            while ((linea = br.readLine()) != null) {
                String[] fila = linea.split(",");
                datos.add(fila);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datos;
    }

    // Método para guardar los datos en el archivo CSV
    private static void guardarArchivoCSV(String archivoCSV, List<String[]> datos) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCSV));
            bw.write("Id,Nom,Cognom,Departament,Salari");
            bw.newLine();
            for (String[] fila : datos) {
                bw.write(String.join(",", fila));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
