import java.io.*;

public class GestorEstudiants {
    // Longitud fixa de cada registre
    private static final int LONGITUD_REGISTRE = 28; // 4 (enter) + 20 (cadena) + 4 (float)

    public static void main(String[] args) {
        // Definir el nom de l'arxiu que contindrà les dades dels estudiants
        String nomArxiu = "estudiants.dat";

        try {
            // Crear o obrir l'arxiu amb mode de lectura i escriptura
            RandomAccessFile arxiu = new RandomAccessFile(nomArxiu, "rw");

            // Menú d'opcions
            boolean sortir = false;
            while (!sortir) {
                System.out.println("----- Menú -----");
                System.out.println("1. Afegir un nou estudiant");
                System.out.println("2. Actualitzar la nota d'un estudiant");
                System.out.println("3. Consultar la nota d'un estudiant");
                System.out.println("4. Sortir");
                System.out.print("Escull una opció: ");

                // Llegir l'opció de l'usuari
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String opcioStr = br.readLine();

                try {
                    int opcio = Integer.parseInt(opcioStr);

                    switch (opcio) {
                        case 1:
                            // Afegir un nou estudiant
                            afegirEstudiant(arxiu);
                            break;
                        case 2:
                            // Actualitzar la nota d'un estudiant
                            actualitzarNota(arxiu);
                            break;
                        case 3:
                            // Consultar la nota d'un estudiant
                            consultarNota(arxiu);
                            break;
                        case 4:
                            // Sortir del programa
                            sortir = true;
                            break;
                        default:
                            System.out.println("Opció no vàlida. Si us plau, tria una opció vàlida.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Opció no vàlida. Si us plau, tria una opció vàlida.");
                }
            }

            // Tanquem l'arxiu després de l'ús
            arxiu.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Mètode per afegir un nou estudiant al fitxer
    public static void afegirEstudiant(RandomAccessFile arxiu) throws IOException {
        // Llegir les dades de l'estudiant
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Introdueix el número de registre: ");
        int numeroRegistre = Integer.parseInt(br.readLine());
        System.out.print("Introdueix el nom de l'estudiant: ");
        String nom = br.readLine();
        System.out.print("Introdueix la nota de l'estudiant: ");
        float nota = Float.parseFloat(br.readLine());

        // Assegurar que el nom tingui una longitud màxima de 20 caràcters
        if (nom.length() > 20) {
            nom = nom.substring(0, 20);
        }

        // Escriure les dades a l'arxiu
        arxiu.seek(arxiu.length()); // Mover el punter al final de l'arxiu
        arxiu.writeInt(numeroRegistre);
        arxiu.writeUTF(nom);
        arxiu.writeFloat(nota);
        System.out.println("Estudiant afegit amb èxit.");
    }

    // Mètode per consultar la nota d'un estudiant pel seu número de registre
    public static void consultarNota(RandomAccessFile arxiu) throws IOException {
        // Llegir el número de registre a consultar
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Introdueix el número de registre de l'estudiant: ");
        int numeroRegistre = Integer.parseInt(br.readLine());

        // Reiniciar el punter al principi de l'arxiu
        arxiu.seek(0);

        while (arxiu.getFilePointer() < arxiu.length()) {
            int registre = arxiu.readInt();
            String nom = arxiu.readUTF();  // Leer el nombre aquí
        
            if (registre == numeroRegistre) {
                float nota = arxiu.readFloat();
        
                System.out.println("Estudiant " + registre + ": " + nom + ", Nota: " + nota);
                return; // Sortir si es troba l'estudiant
            } else {
                // Avançar el punter a l'inici del següent registre
                arxiu.seek(arxiu.getFilePointer() + 4); // Avanzar solo por la nota
            }
        }

        System.out.println("Estudiant amb número de registre " + numeroRegistre + " no trobat.");
    }

    // Mètode per actualitzar la nota d'un estudiant pel seu número de registre
    public static void actualitzarNota(RandomAccessFile arxiu) throws IOException {
        // Llegir el número de registre a actualitzar
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Introdueix el número de registre de l'estudiant: ");
        int numeroRegistre = Integer.parseInt(br.readLine());

        // Reiniciar el punter al principi de l'arxiu
        arxiu.seek(0);

        while (arxiu.getFilePointer() < arxiu.length()) {
            int registre = arxiu.readInt();
            String nom = arxiu.readUTF();  // Leer el nombre aquí
        
            if (registre == numeroRegistre) {
                System.out.print("Introdueix la nova nota: ");
                float novaNota = Float.parseFloat(br.readLine());
                arxiu.writeFloat(novaNota); // Escriure la nova nota
                System.out.println("Nota actualitzada per a l'estudiant " + numeroRegistre);
                return; // Sortir si es troba l'estudiant
            } else {
                // Avançar el punter a l'inici del següent registre
                arxiu.seek(arxiu.getFilePointer() + 4); // Avanzar solo por la nota
            }
        }
        System.out.println("Estudiant amb número de registre " + numeroRegistre + " no trobat.");
    }
}