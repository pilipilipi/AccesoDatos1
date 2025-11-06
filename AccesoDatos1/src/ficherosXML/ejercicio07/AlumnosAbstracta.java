package ficherosXML.ejercicio07;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public abstract class AlumnosAbstracta {
	final static int NUM_ALUMNOS = 1;

	public void generarXml(String nombreFichero) {
		List<Alumno> alumnos = leerAlumno();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();

			Document document = implementation.createDocument(null, "Alumnos", null);
			document.setXmlVersion("1.0");
			

			for (Alumno alumno : alumnos) {
				Element alumnoE = crearElementoAlumno(document, alumno);
				document.getDocumentElement().appendChild(alumnoE);
			}

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			//transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(document), new StreamResult(new File(nombreFichero)));

			System.out.println("Fichero XML generado correctamente: " + nombreFichero);

		} catch (TransformerException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	protected abstract Element crearElementoAlumno(Document document, Alumno alumno);

	protected static void crearElemento(String dato, String valor, Element padre, Document document) {
		Element elem = document.createElement(dato);
		Text text = document.createTextNode(valor);
		elem.appendChild(text);
		padre.appendChild(elem);
	}

	protected static List<Alumno> leerAlumno() {
		Scanner sc = new Scanner(System.in);
		List<Alumno> alumnos = new ArrayList<>();

		for (int i = 0; i < NUM_ALUMNOS; i++) {
			System.out.println("NIA:");
			int nia = Integer.parseInt(sc.nextLine());

			System.out.println("Nombre:");
			String nombre = sc.nextLine();

			System.out.println("Apellidos:");
			String apellidos = sc.nextLine();

			System.out.println("Ciclo:");
			String ciclo = sc.nextLine();

			System.out.println("Curso:");
			String curso = sc.nextLine();

			System.out.println("Grupo:");
			String grupo = sc.nextLine();

			System.out.println("Género (f/m):");
			char genero = sc.nextLine().charAt(0);

			System.out.println("Fecha de nacimiento dd/MM/yyyy:");
			String fecha = sc.nextLine();

			alumnos.add(new Alumno(nia, nombre, apellidos, ciclo, curso, grupo, genero, fecha));
		}
		sc.close();
		return alumnos;
	}
}
