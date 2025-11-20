package ficherosJson.ejercicio10b;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ficherosXML.ejercicio07.Alumno;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Escribir {

	final static int NUM_ALUMNOS = 5;

	public static void main(String[] args) {
		List<Alumno> alumnos = leerAlumno();

		// registerTypeAdapter para que deje poner la fecha
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
				.create();

		// pasar la lista de alumnos a JSON
		String jsonOutput = gson.toJson(alumnos);

		// escribir cadena JSON en fichero
		try (FileWriter writer = new FileWriter("alumnos.json")) {
			writer.write(jsonOutput);
			System.out.println("Se han guardado " + alumnos.size() + " registros de alumnos");
		} catch (IOException e) {
			System.err.println("Error al escribir el fichero JSON: " + e.getMessage());
			e.printStackTrace();
		}
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
