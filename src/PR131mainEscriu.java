import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PR131mainEscriu {
    public static void main(String[] args) {
        PR131hashmap objetoHashMap = new PR131hashmap();

        // Agrega datos al HashMap
        objetoHashMap.getHashMap().put("Persona1", 25);
        objetoHashMap.getHashMap().put("Persona2", 30);
        objetoHashMap.getHashMap().put("Persona3", 35);

        try {
            // Crea un archivo PR131HashMapData.ser y escribe el objeto serializado
            FileOutputStream fos = new FileOutputStream("PR131HashMapData.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(objetoHashMap);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
