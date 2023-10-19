import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.xpath.*;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PR142main {
    private static final String XML_FILE = "cursos.xml";

    public static void main(String[] args) throws TransformerException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(XML_FILE);

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();

            Scanner scanner = new Scanner(System.in);
            int choice  = 0;
            boolean validChoice;
            do {
                printMenu();
                validChoice = false;
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea
                    validChoice = true;
                    switch (choice) {
                        case 1:
                            listarCursosTutorsAlumnos(doc, xpath);
                            break;
                        case 2:
                            mostrarModulosPorCurso(doc, xpath, scanner);
                            break;
                        case 3:
                            listarAlumnosPorCurso(doc, xpath, scanner);
                            break;
                        case 4:
                            agregarAlumnoACurso(doc, xpath, scanner);
                            break;
                        case 5:
                            eliminarAlumnoDeCurso(doc, xpath, scanner);
                            break;
                        case 0:
                            System.out.println("Adéu!");
                            break;
                        default:
                            System.out.println("Opció no vàlida.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Opció no vàlida. Si us plau, introdueix un número.");
                    scanner.nextLine(); // Consumir entrada incorrecta
                }
            } while (!validChoice || choice != 0);

        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    private static void printMenu() {
        System.out.println("Menú:");
        System.out.println("1. Llistar ids de cursos, tutors i total d’alumnes.");
        System.out.println("2. Mostrar ids i titols dels mòduls a partir d'un id de curs.");
        System.out.println("3. Llistar alumnes d’un curs.");
        System.out.println("4. Afegir un alumne a un curs.");
        System.out.println("5. Eliminar un alumne d'un curs.");
        System.out.println("0. Sortir");
        System.out.print("Tria una opció: ");
    }


    private static void listarCursosTutorsAlumnos(Document doc, XPath xpath) throws XPathExpressionException {
        NodeList cursos = (NodeList) xpath.evaluate("/cursos/curs", doc, XPathConstants.NODESET);
        for (int i = 0; i < cursos.getLength(); i++) {
            Element curso = (Element) cursos.item(i);
            String id = curso.getAttribute("id");
            String tutor = xpath.evaluate("tutor", curso);
            NodeList alumnos = (NodeList) xpath.evaluate("alumnes/alumne", curso, XPathConstants.NODESET);
            int totalAlumnos = alumnos.getLength();
            System.out.println("Curso ID: " + id);
            System.out.println("Tutor: " + tutor);
            System.out.println("Total de Alumnos: " + totalAlumnos);
            System.out.println();
        }
    }

    private static void mostrarModulosPorCurso(Document doc, XPath xpath, Scanner scanner) throws XPathExpressionException {
        System.out.print("Introdueix l'ID del curs: ");
        String cursoIdBuscado = scanner.nextLine();
        String xpathQuery = String.format("/cursos/curs[@id='%s']/moduls/modul", cursoIdBuscado);
        NodeList modulos = (NodeList) xpath.evaluate(xpathQuery, doc, XPathConstants.NODESET);
        if (modulos.getLength() == 0) {
            System.out.println("ID del curs no trobada.");
        } else {
            System.out.println("Mòduls per al curs ID: " + cursoIdBuscado);
            for (int i = 0; i < modulos.getLength(); i++) {
                Element modulo = (Element) modulos.item(i);
                String moduloId = modulo.getAttribute("id");
                String tituloModulo = xpath.evaluate("titol", modulo);
                System.out.println("Mòdul ID: " + moduloId);
                System.out.println("Títol: " + tituloModulo);
            }
        }
    }
    

    private static void listarAlumnosPorCurso(Document doc, XPath xpath, Scanner scanner) throws XPathExpressionException {
        System.out.print("Introdueix l'ID del curs: ");
        String cursoIdListar = scanner.nextLine();
        String xpathQueryListarAlumnos = String.format("/cursos/curs[@id='%s']/alumnes/alumne", cursoIdListar);
        NodeList alumnosCurso = (NodeList) xpath.evaluate(xpathQueryListarAlumnos, doc, XPathConstants.NODESET);
        if (alumnosCurso.getLength() == 0) {
            System.out.println("ID del curs no trobada.");
        } else {
            System.out.println("Alumnes per al curs ID: " + cursoIdListar);
            for (int i = 0; i < alumnosCurso.getLength(); i++) {
                Element alumno = (Element) alumnosCurso.item(i);
                String nombreAlumno = alumno.getTextContent();
                System.out.println("Nom de l'Alumne: " + nombreAlumno);
            }
        }
    }
    

    private static void agregarAlumnoACurso(Document doc, XPath xpath, Scanner scanner) throws XPathExpressionException, TransformerException {
        System.out.print("Introdueix l'ID del curs al que vols afegir l'alumne: ");
        String cursoIdAgregarAlumno = scanner.nextLine();
    
        // Verificar si el curso con la ID ingresada existe
        String xpathQueryCurso = String.format("/cursos/curs[@id='%s']", cursoIdAgregarAlumno);
        Node cursoDestino = (Node) xpath.evaluate(xpathQueryCurso, doc, XPathConstants.NODE);
    
        if (cursoDestino == null) {
            System.out.println("ID del curs no trobada.");
        } else {
            System.out.print("Introdueix el nom de l'alumne: ");
            String nuevoAlumnoNombre = scanner.nextLine();
    
            if (nuevoAlumnoNombre.trim().isEmpty()) {
                System.out.println("El nom de l'alumne no pot estar buit.");
            } else {
                // Crear el elemento 'alumne' y agregarlo como hijo del curso
                Element nuevoAlumno = doc.createElement("alumne");
                nuevoAlumno.setTextContent(nuevoAlumnoNombre);
    
                NodeList cursos = doc.getElementsByTagName("curs");
                for (int i = 0; i < cursos.getLength(); i++) {
                    Element curso = (Element) cursos.item(i);
                    String id = curso.getAttribute("id");
                    if (id.equals(cursoIdAgregarAlumno)) {
                        Element alumnes = (Element) curso.getElementsByTagName("alumnes").item(0);
                        alumnes.appendChild(nuevoAlumno);
    
                        guardarCambiosEnArchivo(doc);
                        System.out.println("Alumne afegit al curs ID: " + cursoIdAgregarAlumno);
                        break;
                    }
                }
            }
        }
    }
    
    

    private static void eliminarAlumnoDeCurso(Document doc, XPath xpath, Scanner scanner) throws XPathExpressionException, TransformerException {
        System.out.print("Introdueix l'ID del curs del que vols eliminar l'alumne: ");
        String cursoIdEliminarAlumno = scanner.nextLine();
    
        // Verificar si el curso con la ID ingresada existe
        String xpathQueryCurso = String.format("/cursos/curs[@id='%s']", cursoIdEliminarAlumno);
        Node cursoEliminarAlumno = (Node) xpath.evaluate(xpathQueryCurso, doc, XPathConstants.NODE);
    
        if (cursoEliminarAlumno == null) {
            System.out.println("ID del curs no trobada.");
        } else {
            System.out.print("Introdueix el nom de l'alumne a eliminar: ");
            String alumnoAEliminar = scanner.nextLine();
    
            // Buscar y eliminar el alumno del curso
            NodeList alumnosEliminar = (NodeList) xpath.evaluate("alumnes/alumne[text()='" + alumnoAEliminar + "']", cursoEliminarAlumno, XPathConstants.NODESET);
            if (alumnosEliminar.getLength() == 0) {
                System.out.println("Alumne no trobat en el curs.");
            } else {
                for (int i = 0; i < alumnosEliminar.getLength(); i++) {
                    Element alumnoEliminar = (Element) alumnosEliminar.item(i);
                    Element parent = (Element) alumnoEliminar.getParentNode();
                    parent.removeChild(alumnoEliminar);
                }
    
                guardarCambiosEnArchivo(doc);
                System.out.println("Alumne eliminat del curs ID: " + cursoIdEliminarAlumno);
            }
        }
    }
    

    private static void guardarCambiosEnArchivo(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(XML_FILE);
        transformer.transform(source, result);
        System.out.println("Canvis guardats a l'arxiu " + XML_FILE);
    }
}
