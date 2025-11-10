package ficherosXML.ejercicio07;

import org.w3c.dom.*;

public class MainAlumnosXml extends AlumnosAbstracta {
	final static int NUM_ALUMNOS = 1;

	public static void main(String[] args) {
		new MainAlumnosXml().generarXml("AlumnosDOMObjetos.xml");

	}

	@Override
	protected void anyadirNia(Document document, Alumno alumno, Element alumnoE) {
		crearElementoAlumno("NIA", Integer.toString(alumno.getNia()), alumnoE, document);
	}

	@Override
	protected void anyadirNombre(Document document, Alumno alumno, Element alumnoE) {
		crearElementoAlumno("nombre", alumno.getNombre(), alumnoE, document);
	}

	@Override
	protected void anyadirApellidos(Document document, Alumno alumno, Element alumnoE) {
		crearElementoAlumno("apellidos", alumno.getApellidos(), alumnoE, document);
	}

	@Override
	protected void anyadirCiclo(Document document, Alumno alumno, Element alumnoE) {
		crearElementoAlumno("ciclo", alumno.getCiclo(), alumnoE, document);
	}

	@Override
	protected void anyadirCurso(Document document, Alumno alumno, Element alumnoE) {
		crearElementoAlumno("curso", alumno.getCurso(), alumnoE, document);
	}

	@Override
	protected void anyadirGrupo(Document document, Alumno alumno, Element alumnoE) {
		crearElementoAlumno("grupo", alumno.getGrupo(), alumnoE, document);
	}

	@Override
	protected void anyadirGenero(Document document, Alumno alumno, Element alumnoE) {
		crearElementoAlumno("genero", Character.toString(alumno.getGenero()), alumnoE, document);
	}

	@Override
	protected void anyadirFecha(Document document, Alumno alumno, Element alumnoE) {
		crearElementoAlumno("fecha", alumno.getFechaString(), alumnoE, document);
	}

}
