package ficherosBinario.ejercicio01;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Pilar
 * @version 1.0
 */

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		String fichero, ruta;

		System.out.println("Dime el nombre del fichero");
		fichero = sc.nextLine();

		System.out.println("Dime la ruta");
		ruta = sc.nextLine();

		File f = new File(ruta + "\\" + fichero + ".dat");

		FileOutputStream salida = new FileOutputStream(f);
		DataOutputStream salidaDatos = null;

		try {
			salidaDatos = new DataOutputStream(salida);

			do {
				Alumno a = leerAlumno(sc);

				salidaDatos.writeInt(a.getNia());
				salidaDatos.writeUTF(a.getNombre());
				salidaDatos.writeUTF(a.getApellidos());
				salidaDatos.writeChar(a.getGenero());
				salidaDatos.writeUTF(a.getFechaString());
				salidaDatos.writeUTF(a.getCiclo());
				salidaDatos.writeUTF(a.getCurso());
				salidaDatos.writeUTF(a.getGrupo());
				salidaDatos.flush();

				System.out.println("Para introducir otro alumno introduzca 1, para salir, introduzca 0");
			} while (Integer.parseInt(sc.nextLine()) != 0);

			salidaDatos.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sc.close();
	}

	/**
	 * @param sc el objeto
	 * @return un nuevo objeto Alumno con los datos introducidos
	 */

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
