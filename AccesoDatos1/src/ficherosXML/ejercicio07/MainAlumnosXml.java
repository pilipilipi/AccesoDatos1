package ficherosXML.ejercicio07;

import org.w3c.dom.*;

public class MainAlumnosXml extends AlumnosAbstracta {
	final static int NUM_ALUMNOS = 1;

	public static void main(String[] args) {
		new MainAlumnosXml().generarXml("AlumnosDOMObjetos.xml");
		
	}

	@Override
	protected Element crearElementoAlumno(Document document, Alumno alumno) {
		// Creamos el nodo alumno
		Element alumnoE = document.createElement("alumno");

		crearElemento("nia", Integer.toString(alumno.getNia()), alumnoE, document);
		crearElemento("nombre", alumno.getNombre(), alumnoE, document);
		crearElemento("ciclo", alumno.getCiclo(), alumnoE, document);
		crearElemento("apellidos", alumno.getApellidos(), alumnoE, document);
		crearElemento("curso", alumno.getCurso(), alumnoE, document);
		crearElemento("grupo", alumno.getGrupo(), alumnoE, document);
		crearElemento("genero", Character.toString(alumno.getGenero()), alumnoE, document);
		crearElemento("fecha", alumno.getFechaString(), alumnoE, document);
		
		return alumnoE;
	}

}
