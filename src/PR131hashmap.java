import java.io.*;
import java.util.HashMap;

// Clase que implementa Serializable y contiene un HashMap
class PR131hashmap implements Serializable {
    private HashMap<String, Integer> hashMap;

    public PR131hashmap() {
        hashMap = new HashMap<>();
    }

    public HashMap<String, Integer> getHashMap() {
        return hashMap;
    }
}