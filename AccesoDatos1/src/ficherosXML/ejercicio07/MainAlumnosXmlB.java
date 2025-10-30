package ficherosXML.ejercicio07;

import org.w3c.dom.*;

public class MainAlumnosXmlB extends MainAlumnosXml {

	public static void main(String[] args) {
		new MainAlumnosXmlB().generarXml("AlumnosDOMAtributos.xml");
	}

	@Override
	protected Element crearElementoAlumno(Document document, Alumno alumno) {
		Element alumnoE = document.createElement("alumno");

		alumnoE.setAttribute("nia", Integer.toString(alumno.getNia()));
		alumnoE.setAttribute("nombre", alumno.getNombre());
		alumnoE.setAttribute("apellidos", alumno.getApellidos());
		alumnoE.setAttribute("ciclo", alumno.getCiclo());
		alumnoE.setAttribute("curso", alumno.getCurso());
		alumnoE.setAttribute("grupo", alumno.getGrupo());
		alumnoE.setAttribute("genero", Character.toString(alumno.getGenero()));
		alumnoE.setAttribute("fecha", alumno.getFechaString());

		return alumnoE;
	}
}
