import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PR120ReadFile {
    public static void main(String[] args) {
        try {
            // Obre el fitxer actual
            FileReader fileReader = new FileReader("PR120ReadFile.java");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            int lineNumber = 1;

            // Llegeix i mostra cada línia del fitxer
            while ((line = bufferedReader.readLine()) != null) {
                System.out.printf("%d: %s%n", lineNumber, line);
                lineNumber++;
            }

            // Tanca el fitxer
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
