import java.io.*;
import java.util.*;

public class PR133mainTreballadors {
    public static void main(String[] args) {
        // Definir el nom de l'arxiu CSV
        String arxiuCSV = "PR133treballadors.csv";

        // Crear un Scanner per llegir l'entrada de l'usuari
        Scanner scanner = new Scanner(System.in);

        String idTreballador; // Variable per emmagatzemar l'identificador introduït per l'usuari
        String dadaAModificar; // Variable per emmagatzemar la dada a modificar

        // Bucle per assegurar-se que l'usuari introdueixi un identificador vàlid
        do {
            // Demanar a l'usuari l'identificador de treballador com a cadena de text
            System.out.print("Introdueix l'identificador del treballador: ");
            idTreballador = scanner.nextLine().trim(); // Llegir l'identificador com una cadena i eliminar els espais en blanc
        } while (!existeixId(arxiuCSV, idTreballador)); // Continuar preguntant fins que es trobi un identificador existent

        // Bucle per assegurar-se que l'usuari introdueixi una dada vàlida per modificar
        do {
            // Demanar a l'usuari quina dada vol modificar
            System.out.print("Quina dada vols modificar (Nom, Cognom, Departament, Salari): ");
            dadaAModificar = scanner.nextLine().toLowerCase(); // Llegir la dada i convertir-la a minúscules
        } while (!dadaAModificar.equals("nom") && !dadaAModificar.equals("cognom") && !dadaAModificar.equals("departament") && !dadaAModificar.equals("salari"));

        // Inicialitzar la variable de salari
        double salari = 0.0;

        // Validar el valor de salari si es demana la modificació de salari
        if (dadaAModificar.equals("salari")) {
            do {
                // Demanar a l'usuari el nou valor de salari
                System.out.print("Introdueix el nou valor de salari (major a 0): ");
                String nouValor = scanner.nextLine();

                try {
                    salari = Double.parseDouble(nouValor);
                    if (salari <= 0) {
                        System.out.println("El salari ha de ser major a 0. Torna a intentar.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no vàlida. Si us plau, introdueix un número vàlid per al salari.");
                }
            } while (salari <= 0);
        } else {
            // Si no s'està modificant el salari, simplement demanar el nou valor
            System.out.print("Introdueix el nou valor: ");
            String nouValor = scanner.nextLine();

            // Intentar convertir el valor a double si no és una modificació de salari
            try {
                salari = Double.parseDouble(nouValor);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no vàlida. Si us plau, introdueix un número vàlid.");
            }
        }

        // Llegir les línies de l'arxiu CSV i fer les modificacions necessàries
        try {
            File arxiu = new File(arxiuCSV);
            BufferedReader br = new BufferedReader(new FileReader(arxiu));
            String linia;
            StringBuilder nouContingut = new StringBuilder();

            while ((linia = br.readLine()) != null) {
                String[] parts = linia.split(",");
                String id = parts[0].trim(); // Llegir l'identificador com una cadena i eliminar els espais en blanc

                if (id.equals(idTreballador)) {
                    // Modificar la dada especificada
                    int columnaAModificar = -1;

                    switch (dadaAModificar) {
                        case "nom":
                            columnaAModificar = 1;
                            break;
                        case "cognom":
                            columnaAModificar = 2;
                            break;
                        case "departament":
                            columnaAModificar = 3;
                            break;
                        case "salari":
                            columnaAModificar = 4;
                            break;
                    }

                    if (columnaAModificar != -1) {
                        parts[columnaAModificar] = Double.toString(salari);
                    }
                }

                nouContingut.append(String.join(",", parts)).append("\n");
            }

            br.close();

            // Actualitzar l'arxiu CSV amb les modificacions
            FileWriter fw = new FileWriter(arxiu);
            fw.write(nouContingut.toString());
            fw.close();

            System.out.println("Modificació realitzada amb èxit.");
        } catch (IOException e) {
            System.err.println("Error en llegir o escriure l'arxiu.");
            e.printStackTrace();
        }
    }

    // Funció per comprovar si un identificador existeix a l'arxiu CSV
    private static boolean existeixId(String arxiuCSV, String id) {
        try {
            File arxiu = new File(arxiuCSV);
            BufferedReader br = new BufferedReader(new FileReader(arxiu));
            String linia;

            while ((linia = br.readLine()) != null) {
                String[] parts = linia.split(",");
                String idTreballador = parts[0].trim(); // Llegir l'identificador com una cadena i eliminar els espais en blanc

                if (idTreballador.equals(id)) {
                    br.close();
                    return true; // L'identificador existeix
                }
            }

            br.close();
        } catch (IOException e) {
            System.err.println("Error en llegir l'arxiu.");
            e.printStackTrace();
        }

        System.out.println("Identificador no trobat. Torna a intentar.");
        return false; // L'identificador no existeix
    }
}
