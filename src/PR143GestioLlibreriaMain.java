import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class PR143GestioLlibreriaMain {
    public static void main(String[] args) {
        try {
            // Pas 1: Llegir el fitxer llibres_input.json i carregar-lo a una estructura de dades en memòria.
            JSONArray llibresArray = (JSONArray) new JSONParser().parse(new FileReader("llibres_input.json"));

            // Pas 2: Modificació - Canviar l'any de publicació del llibre amb id 1 a 1995.
            for (Object obj : llibresArray) {
                JSONObject llibre = (JSONObject) obj;
                long id = (long) llibre.get("id");
                if (id == 1) {
                    llibre.put("any", 1995);
                    break;
                }
            }

            // Pas 3: Afegit - Afegir un nou llibre amb id 4.
            JSONObject nouLlibre = new JSONObject();
            nouLlibre.put("id", 4);
            nouLlibre.put("títol", "Històries de la ciutat");
            nouLlibre.put("autor", "Miquel Soler");
            nouLlibre.put("any", 2022);
            llibresArray.add(nouLlibre);

            // Pas 4: Esborrat - Esborrar el llibre amb id 2.
            Iterator<Object> iterator = llibresArray.iterator();
            while (iterator.hasNext()) {
                JSONObject llibre = (JSONObject) iterator.next();
                long id = (long) llibre.get("id");
                if (id == 2) {
                    iterator.remove();
                    break;
                }
            }

            // Pas 5: Guardar les dades modificades en un fitxer nou anomenat llibres_output.json.
            try (FileWriter fileWriter = new FileWriter("llibres_output.json")) {
                fileWriter.write(llibresArray.toJSONString());
                System.out.println("Dades modificades guardades a llibres_output.json.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
