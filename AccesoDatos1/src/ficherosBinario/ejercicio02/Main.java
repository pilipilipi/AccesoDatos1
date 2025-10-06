package ficherosBinario.ejercicio02;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import pruebas.prueba01.Alumno;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		String fichero, ruta;

		System.out.println("Dime el nombre del fichero");
		fichero = sc.nextLine();

		System.out.println("Dime la ruta");
		ruta = sc.nextLine();

		File f = new File(ruta + "\\" + fichero + ".dat");

		try (ObjectOutputStream salidaDatos = new ObjectOutputStream(new FileOutputStream(f))) {

			do {
				Alumno a = leerAlumno(sc);
				salidaDatos.writeObject(a);

				System.out.println("Para introducir otro alumno introduzca 1, para salir, introduzca 0");
			} while (Integer.parseInt(sc.nextLine()) != 0);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try (ObjectInputStream entradaDatos = new ObjectInputStream(new FileInputStream(f))) {

			while (true) {
				try {
					Alumno a = (Alumno) entradaDatos.readObject();
					System.out.println(a);

				} catch (EOFException e) {
					// Fin del archivo alcanzado
					break;
				}
			}
			entradaDatos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		sc.close();
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

}
