import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class PR140main {
    public static void main(String[] args) {
        try {
            // Crear un objecte DocumentBuilderFactory per crear un analitzador XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            // Carregar el fitxer XML
            Document doc = builder.parse(new File("persones.xml"));
            
            // Obtindre la llista d'elements "persona"
            NodeList personas = doc.getElementsByTagName("persona");
            
            // Mostrar les dades amb columnes alineades
            System.out.println("Nom       | Cognom    | Edat | Ciutat");
            System.out.println("---------------------------------------");
            
            for (int i = 0; i < personas.getLength(); i++) {
                Element persona = (Element) personas.item(i);
                String nom = persona.getElementsByTagName("nom").item(0).getTextContent();
                String cognom = persona.getElementsByTagName("cognom").item(0).getTextContent();
                String edat = persona.getElementsByTagName("edat").item(0).getTextContent();
                String ciutat = persona.getElementsByTagName("ciutat").item(0).getTextContent();
                
                System.out.printf("%-10s| %-10s| %-4s| %-10s%n", nom, cognom, edat, ciutat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
