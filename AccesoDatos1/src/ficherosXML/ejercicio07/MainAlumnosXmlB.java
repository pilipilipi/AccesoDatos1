package ficherosXML.ejercicio07;

import org.w3c.dom.*;

public class MainAlumnosXmlB extends AlumnosAbstracta {

	public static void main(String[] args) {
		new MainAlumnosXmlB().generarXml("AlumnosDOMAtributos.xml");
	}

	@Override
	protected void anyadirNia(Document document, Alumno alumno, Element alumnoE) {
		crearElementoAlumnoatt("NIA", Integer.toString(alumno.getNia()), alumnoE);
	}

	@Override
	protected void anyadirNombre(Document document, Alumno alumno, Element alumnoE) {
		crearElementoAlumnoatt("nombre", alumno.getNombre(), alumnoE);
	}

	@Override
	protected void anyadirApellidos(Document document, Alumno alumno, Element alumnoE) {
		crearElementoAlumnoatt("apellidos", alumno.getApellidos(), alumnoE);
	}

	@Override
	protected void anyadirCiclo(Document document, Alumno alumno, Element alumnoE) {
		crearElementoAlumnoatt("ciclo", alumno.getCiclo(), alumnoE);
	}

	@Override
	protected void anyadirCurso(Document document, Alumno alumno, Element alumnoE) {
		crearElementoAlumnoatt("curso", alumno.getCurso(), alumnoE);
	}

	@Override
	protected void anyadirGrupo(Document document, Alumno alumno, Element alumnoE) {
		crearElementoAlumnoatt("grupo", alumno.getGrupo(), alumnoE);
	}

	@Override
	protected void anyadirGenero(Document document, Alumno alumno, Element alumnoE) {
		crearElementoAlumnoatt("genero", Character.toString(alumno.getGenero()), alumnoE);
	}

	@Override
	protected void anyadirFecha(Document document, Alumno alumno, Element alumnoE) {
		crearElementoAlumnoatt("fecha", alumno.getFechaString(), alumnoE);
	}

//	@Override
//	protected Element crearElementoAlumno(Alumno alumno) {
//		//Element alumnoE = document.createElement("alumno");
//
//		alumnoE.setAttribute("nia", Integer.toString(alumno.getNia()));
//		alumnoE.setAttribute("nombre", alumno.getNombre());
//		alumnoE.setAttribute("apellidos", alumno.getApellidos());
//		alumnoE.setAttribute("ciclo", alumno.getCiclo());
//		alumnoE.setAttribute("curso", alumno.getCurso());
//		alumnoE.setAttribute("grupo", alumno.getGrupo());
//		alumnoE.setAttribute("genero", Character.toString(alumno.getGenero()));
//		alumnoE.setAttribute("fecha", alumno.getFechaString());
//
//		//return alumnoE;
//	}

}
