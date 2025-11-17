package javaBBDD.ejercicio11;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ficherosXML.ejercicio07.Alumno;

public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		List<Alumno> alumnos = new ArrayList<>();
		File f = new File("alumnosBBDD.dat");
		int opcion;

		do {
			opcion = elegir(sc);
			menu(opcion, alumnos, f, sc);

		} while (opcion != 0);

		sc.close();
	}

	public static int elegir(Scanner sc) {
		System.out.println("\r\nElige opción: \r\n" 
				+ "1. Insertar un nuevo alumno.\r\n"
				+ "2. Mostar todos los alumnos (en consola).\r\n"
				+ "3. Guardar todos los alumnos en un fichero (tú eliges el formato del fichero, pero no puede ser XML ni JSON).\r\n"
				+ "4. Leer alumnos de un fichero (con el formato anterior), y guardarlo en una BD.\r\n"
				+ "5. Modificar el nombre de un alumno guardado en la base de datos a partir de su Primary Key (PK).\r\n"
				+ "6. Eliminar un alumno a partir de su (PK).\r\n"
				+ "7. Eliminar los alumnos que su apellido contengan la palabra dada\r\n" + "por el usuario.\r\n"
				+ "8. Guardar todos los alumnos en un fichero XML o JSON.\r\n"
				+ "9. Leer un fichero XML o JSON de alumnos (con en formato anterior) y guardarlos en la BD. \r\n"
				+ "0. Salir \r\n");

		return Integer.parseInt(sc.nextLine());
	}

	public static void menu(int opcion, List<Alumno> alumnos, File f, Scanner sc) {

		switch (opcion) {
		case 1:
			leerAlumno(alumnos, sc);
			break;

		case 2:
			mostrarAlumnos(alumnos);
			break;

		case 3:
			guardarAlumnos(alumnos, f);
			break;

		case 4:

			break;

		case 5:

			break;

		case 6:

			break;

		case 7:

			break;

		case 8:

			break;

		case 9:

			break;

		case 0:

			break;

		default:

			break;
		}

	}

	public static void leerAlumno(List<Alumno> alumnos, Scanner sc) {

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

		alumnos.add(new Alumno(nia, nombre, apellidos, ciclo, curso, grupo, genero, fecha));
		//return alumnos;
	}
	
	public static void mostrarAlumnos(List<Alumno> alumnos) {
		
		for(Alumno a : alumnos) {
			System.out.println(a);
		}
	}
	
	public static void guardarAlumnos(List<Alumno> alumnos, File f) {
		
		try (ObjectOutputStream salidaDatos = new ObjectOutputStream(new FileOutputStream(f))) {
			
			for(Alumno a : alumnos) {
				salidaDatos.writeObject(a);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}









