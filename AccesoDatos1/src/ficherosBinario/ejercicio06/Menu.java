package ficherosBinario.ejercicio06;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Menu {

	private static File ficheroEnUso;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int opcion;

		do {
			opcion = mostrarMenu(sc);
		} while (opcion != 0);

		sc.close();
	}

	public static int mostrarMenu(Scanner sc) {

		System.out.println("Elige opcion\n" + "1. Crear fichero vacio\n" + "2. Seleccionar fichero existente\n"
				+ "3. Cargar alumno\n" + "4. Mostrar todos los alumnos\n" + "0. Salir\n");

		int opcion = Integer.parseInt(sc.nextLine());

		switch (opcion) {
		case 1 -> crearFichero(sc);
		case 2 -> seleccionarFichero(sc);
		case 3 -> cargarAlumno(sc);
		case 4 -> mostrarAlumnos();
		case 0 -> System.out.println("Saliendo del programa...");
		default -> System.out.println("Opción no válida.");
		}

		return opcion;
	}

	private static void crearFichero(Scanner sc) {

		System.out.print("Introduce el nombre del fichero (sin extensión): ");
		String nombre = sc.nextLine();

		System.out.print("Introduce la ruta donde guardarlo: ");
		String ruta = sc.nextLine();

		File f = new File(ruta, nombre + ".dat");

		if (f.exists()) {
			System.out.println("El fichero ya existe. Se usará como fichero en uso.");

		} else {

			try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(f))) {

				System.out.println("Fichero creado correctamente.");
				ficheroEnUso = f;

			} catch (IOException e) {
				System.out.println("Error al crear el fichero: " + e.getMessage());
			}
		}

	}

	private static void seleccionarFichero(Scanner sc) {

		System.out.print("Introduce la ruta completa del fichero (.dat): ");

		String ruta = sc.nextLine();
		File f = new File(ruta);

		if (f.exists() && f.isFile()) {

			ficheroEnUso = f;
			System.out.println("Fichero seleccionado correctamente: " + ficheroEnUso.getName());

		} else {

			System.out.println("El fichero no existe o no es válido.");
		}
	}

	private static void cargarAlumno(Scanner sc) {

		if (ficheroEnUso == null) {

			System.out.println("Primero debes crear o seleccionar un fichero.");
			return;
		}

		Alumno a = leerAlumno(sc);

		try (ObjectOutputStream salida = new MiObjectOutputStream(new FileOutputStream(ficheroEnUso, true))) {

			salida.writeObject(a);
			System.out.println("Alumno añadido correctamente.");

		} catch (IOException e) {
			System.out.println("Error al escribir en el fichero: " + e.getMessage());
		}
	}

	private static void mostrarAlumnos() {

		if (ficheroEnUso == null) {
			System.out.println("Primero debes crear o seleccionar un fichero.");

		} else if (!ficheroEnUso.exists() || ficheroEnUso.length() == 0) {
			System.out.println("El fichero está vacío.");
			return;
		}

		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroEnUso))) {

			while (true) {
				Alumno a = (Alumno) entrada.readObject();
				System.out.println(a);
			}

		} catch (EOFException e) {
			System.out.println("Fin de fichero");

		} catch (Exception e) {
			System.out.println("Error al leer el fichero: " + e.getMessage());
		}
	}

	public static Alumno leerAlumno(Scanner sc) {

		System.out.println("NIA:");
		int nia = Integer.parseInt(sc.nextLine());

		System.out.println("Nombre");
		String nombre = sc.nextLine();

		System.out.println("Apellidos:");
		String apellidos = sc.nextLine();

		System.out.println("Ciclo:");
		String ciclo = sc.nextLine();

		System.out.println("Curso:");
		String curso = sc.nextLine();

		System.out.println("Grupo:");
		String grupo = sc.nextLine();

		System.out.println("Género (f/m)");
		char genero = sc.nextLine().charAt(0);

		System.out.println("Fecha de nacimiento dd/MM/yyyy");
		String fecha = sc.nextLine();

		return new Alumno(nia, nombre, apellidos, ciclo, curso, grupo, genero, fecha);
	}

	private static class MiObjectOutputStream extends ObjectOutputStream {
		MiObjectOutputStream(FileOutputStream out) throws IOException {
			super(out);
		}

		@Override
		protected void writeStreamHeader() throws IOException {
			if (ficheroEnUso.length() == 0) {
				super.writeStreamHeader(); // sólo si está vacío
			} else {
				reset(); // si ya tiene datos, no escribas cabecera
			}
		}
	}

}
