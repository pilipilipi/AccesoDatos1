package pruebas.prueba01;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Alumno {

	private int nia;
	private String nombre, apellidos, ciclo, curso, grupo;
	private char genero;

	private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private LocalDate fecha;

	Alumno(int nia, String nombre, String apellidos, String ciclo, String curso, String grupo, char genero, String fecha) {

		this.nia = nia;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.ciclo = ciclo;
		this.curso = curso;
		this.grupo = grupo;

		try {
			this.fecha = LocalDate.parse(fecha, FORMATO);

		} catch (DateTimeParseException e) {
			System.out.println("Formato de fecha incorrecto");
		}
	}

	@Override
	public String toString() {
		return "Alumno [nia=" + nia + ", nombre=" + nombre + ", apellidos=" + apellidos + ", ciclo=" + ciclo
				+ ", curso=" + curso + ", grupo=" + grupo + ", genero=" + genero + ", fecha=" + fecha + "]";
	}

	public int getNia() {
		return nia;
	}

	public void setNia(int nia) {
		this.nia = nia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public static DateTimeFormatter getFormato() {
		return FORMATO;
	}

	
	
}
