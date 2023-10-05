import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class PR131mainLlegeix {
    public static void main(String[] args) {
        PR131hashmap objetoHashMap = null;

        try {
            // Lee el archivo PR131HashMapData.ser y deserializa el objeto
            FileInputStream fis = new FileInputStream("PR131HashMapData.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            objetoHashMap = (PR131hashmap) ois.readObject();
            ois.close();
            fis.close();

            // Muestra el contenido del HashMap por pantalla
            HashMap<String, Integer> hashMap = objetoHashMap.getHashMap();
            for (String nombre : hashMap.keySet()) {
                int edad = hashMap.get(nombre);
                System.out.println("Nombre: " + nombre + ", Edad: " + edad);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}