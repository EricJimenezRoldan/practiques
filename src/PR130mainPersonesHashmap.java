import java.io.*;
import java.util.*;

public class PR130mainPersonesHashmap {
    public static void main(String[] args) {
        // Crea un HashMap con el nombre y la edad de 5 personas
        HashMap<String, Integer> personas = new HashMap<>();
        personas.put("Persona1", 25);
        personas.put("Persona2", 30);
        personas.put("Persona3", 35);
        personas.put("Persona4", 40);
        personas.put("Persona5", 45);

        try {
            // Crea un archivo PR130persones.dat
            FileOutputStream fos = new FileOutputStream("PR130persones.dat");
            DataOutputStream dos = new DataOutputStream(fos);

            // Escribe los datos del HashMap en el archivo
            for (Map.Entry<String, Integer> entry : personas.entrySet()) {
                dos.writeUTF(entry.getKey());
                dos.writeInt(entry.getValue());
            }

            // Cierra el flujo de salida
            dos.close();

            // Lee el archivo PR130persones.dat con DataInputStream
            FileInputStream fis = new FileInputStream("PR130persones.dat");
            DataInputStream dis = new DataInputStream(fis);

            // Muestra el contenido por pantalla
            while (dis.available() > 0) {
                String nombre = dis.readUTF();
                int edad = dis.readInt();
                System.out.println("Nombre: " + nombre + ", Edad: " + edad);
            }

            // Cierra el flujo de entrada
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
