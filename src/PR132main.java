import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PR132main {
    public static void main(String[] args) {
        // Crear objetos con los datos proporcionados
        PR132persona persona1 = new PR132persona("Maria", "López", 36);
        PR132persona persona2 = new PR132persona("Gustavo", "Ponts", 63);
        PR132persona persona3 = new PR132persona("Irene", "Sales", 54);

        List<PR132persona> personas = new ArrayList<>();
        personas.add(persona1);
        personas.add(persona2);
        personas.add(persona3);

        try {
            // Escribir la lista de personas en el archivo "PR132people.dat"
            FileOutputStream fos = new FileOutputStream("PR132people.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(personas);
            oos.close();
            fos.close();

            // Leer la lista de personas desde el archivo
            FileInputStream fis = new FileInputStream("PR132people.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<PR132persona> personasLeidas = (List<PR132persona>) ois.readObject();
            ois.close();
            fis.close();

            // Imprimir la información como una tabla
            System.out.println("Nom\t\tCognom\t\tEdat");
            System.out.println("--------------------------------------");
            for (PR132persona persona : personasLeidas) {
                System.out.println(persona.getNom() + "\t\t" + persona.getCognom() + "\t\t" + persona.getEdat());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}