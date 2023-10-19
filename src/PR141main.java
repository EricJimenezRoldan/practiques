import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class PR141main {
    public static void main(String[] args) {
        try {
            // Crear un objecte DocumentBuilderFactory per crear un analitzador XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Crear un nou document XML
            Document doc = builder.newDocument();

            // Crear l'element arrel "biblioteca"
            Element bibliotecaElement = doc.createElement("biblioteca");
            doc.appendChild(bibliotecaElement);

            // Crear l'element "llibre"
            Element llibreElement = doc.createElement("llibre");
            llibreElement.setAttribute("id", "001");
            bibliotecaElement.appendChild(llibreElement);

            // Afegir els elements fills a "llibre"
            llibreElement.appendChild(createElementWithTextContent(doc, "titol", "El viatge dels venturons"));
            llibreElement.appendChild(createElementWithTextContent(doc, "autor", "Joan Pla"));
            llibreElement.appendChild(createElementWithTextContent(doc, "anyPublicacio", "1998"));
            llibreElement.appendChild(createElementWithTextContent(doc, "editorial", "Edicions Mar"));
            llibreElement.appendChild(createElementWithTextContent(doc, "genere", "Aventura"));
            llibreElement.appendChild(createElementWithTextContent(doc, "pagines", "320"));
            llibreElement.appendChild(createElementWithTextContent(doc, "disponible", "true"));

            // Crear un objecte TransformerFactory per configurar la sortida amb format
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Habilitar la identació

            // Escriure el contingut del document a l'arxiu
            StringWriter stringWriter = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(stringWriter));

            // Crear un objecte FileWriter per escriure el contingut al fitxer
            FileWriter writer = new FileWriter("biblioteca.xml");
            writer.write(stringWriter.toString());
            writer.close();

            System.out.println("Fitxer 'biblioteca.xml' creat amb èxit.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Funció auxiliar per crear elements amb contingut de text
    private static Element createElementWithTextContent(Document doc, String tagName, String textContent) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(textContent));
        return element;
    }
}
