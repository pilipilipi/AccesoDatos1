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
				Element alumnoE = document.createElement("alumno");
				anyadirNia(document, alumno, alumnoE);
				anyadirNombre(document, alumno, alumnoE);
				anyadirApellidos(document, alumno, alumnoE);
				anyadirCiclo(document, alumno, alumnoE);
				anyadirCurso(document, alumno, alumnoE);
				anyadirGrupo(document, alumno, alumnoE);
				anyadirGenero(document, alumno, alumnoE);
				anyadirFecha(document, alumno, alumnoE);
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

	//protected abstract Element crearElementoAlumno(String etiqueta, String cont, Document document, Alumno alumno);
	
	protected abstract void anyadirNia(Document document, Alumno alumno, Element alumnoE);
	protected abstract void anyadirNombre(Document document, Alumno alumno, Element alumnoE);
	protected abstract void anyadirApellidos(Document document, Alumno alumno, Element alumnoE);
	protected abstract void anyadirCiclo(Document document, Alumno alumno, Element alumnoE);
	protected abstract void anyadirCurso(Document document, Alumno alumno, Element alumnoE);
	protected abstract void anyadirGrupo(Document document, Alumno alumno, Element alumnoE);
	protected abstract void anyadirGenero(Document document, Alumno alumno, Element alumnoE);
	protected abstract void anyadirFecha(Document document, Alumno alumno, Element alumnoE);

	protected static void crearElemento(String etiqueta, String cont, Element alumnoE, Document document) {
		Element elem = document.createElement(etiqueta);
		//Text text = document.createTextNode(cont);
		elem.appendChild(document.createTextNode(cont));
		alumnoE.appendChild(elem);
	}
	
	protected void crearElementoAlumno(String etiqueta, String cont, Element alumnoE, Document document) {
		// Creamos el nodo alumno
		//Element alumnoE = document.createElement("alumno");
		crearElemento(etiqueta, cont, alumnoE, document);
		
		//return alumnoE;
	}
	
	protected void crearElementoAlumnoatt(String etiqueta, String cont, Element alumnoE) {		
		//Element alumnoE = document.createElement("alumno");
		alumnoE.setAttribute(etiqueta, cont);

		//return alumnoE;
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
