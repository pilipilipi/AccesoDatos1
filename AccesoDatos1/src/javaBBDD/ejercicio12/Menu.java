package javaBBDD.ejercicio12;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.sql.*;
import java.time.LocalDate;

public class Menu {

	static int acc = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		List<Alumno> alumnos = new ArrayList<>();
		File f = new File("alumnosBBDD.dat");
		File fG = new File("alumnosBBDDGson.json");

		int opcion;

		do {
			opcion = elegir(sc);
			menu(opcion, alumnos, f, sc, fG);

		} while (opcion != 0);

		sc.close();
	}

	public static int elegir(Scanner sc) {
		System.out.println("\r\nElige opción: \r\n" + "1. Insertar un nuevo alumno.\r\n"
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

	public static void menu(int opcion, List<Alumno> alumnos, File f, Scanner sc, File fG) {

		switch (opcion) {
		case 1 -> leerAlumno(alumnos, sc);
		case 2 -> mostrarAlumnos(alumnos);
		case 3 -> guardarAlumnos(alumnos, f);
		case 4 -> conectarBBDD(f);
		case 5 -> modificarNombre(sc);
		case 6 -> borrar(sc);
		case 7 -> borrarPorAp(sc);
		case 8 -> guardarJson(alumnos, fG);
		case 9 -> jsonBBDD(fG);
		case 0 -> System.out.println("Terminado");
		default -> {
			System.out.println("Opción no válida");
		}
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

		System.out.println("Fecha de nacimiento yyyy-MM-dd");
		String fecha = sc.nextLine();

		alumnos.add(new Alumno(nia, nombre, apellidos, ciclo, curso, grupo, genero, fecha));
		acc++;
	}

	public static void mostrarAlumnos(List<Alumno> alumnos) {

		for (Alumno a : alumnos) {
			System.out.println(a);
		}
	}

	public static void guardarAlumnos(List<Alumno> alumnos, File f) {

		try (ObjectOutputStream salidaDatos = new ObjectOutputStream(new FileOutputStream(f))) {

			for (Alumno a : alumnos) {
				salidaDatos.writeObject(a);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void conectarBBDD(File f) {

		try (Connection conexion = conexion()) {

			String sql = "INSERT INTO alumno (nia, nombre, apellidos, ciclo, curso, grupo, genero, fecha)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = conexion.prepareStatement(sql);

			// 4. Leer objetos desde el fichero
			try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(f))) {

				for (int i = 0; i < acc; i++) {
					try {
						Alumno a = (Alumno) entrada.readObject();

						// 5. Rellenar parámetros
						ps.setInt(1, a.getNia());
						ps.setString(2, a.getNombre());
						ps.setString(3, a.getApellidos());
						ps.setString(4, a.getCiclo());
						ps.setString(5, a.getCurso());
						ps.setString(6, a.getGrupo());
						ps.setString(7, String.valueOf(a.getGenero()));
						ps.setDate(8, Date.valueOf(a.getFecha()));

						// 6. Insertar
						ps.executeUpdate();

					} catch (EOFException eof) {
						break;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			// 7. Cerrar la conexión
			ps.close();
			conexion.close();

			System.out.println("Alumnos insertados correctamente en la BD");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection conexion() {

		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/ejercicio11", "root", "manager");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void modificarNombre(Scanner sc) {
		System.out.println("Introduce el nia");
		int nia = Integer.parseInt(sc.nextLine());

		System.out.println("Cambia el nombre");
		String nombre2 = sc.nextLine();

		try (Connection conexion = conexion()) {
			String sql = "UPDATE alumno SET nombre = ? WHERE nia = ?";

			try (PreparedStatement ps = conexion.prepareStatement(sql)) {
				ps.setString(1, nombre2); // nuevo valor para el nombre
				ps.setInt(2, nia); // condición por NIA (usa setInt si es número)

				int filasActualizadas = ps.executeUpdate();

				if (filasActualizadas > 0) {
					System.out.println("El nombre ha sido actualizado correctamente.");
				} else {
					System.out.println("No se encontró ningún alumno con ese NIA.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void borrar(Scanner sc) {
		System.out.println("Introduce el nia");
		int nia = Integer.parseInt(sc.nextLine());

		try (Connection conexion = conexion()) {
			String sql = "DELETE FROM alumno WHERE nia = ?";

			try (PreparedStatement ps = conexion.prepareStatement(sql)) { // nuevo valor para el nombre
				ps.setInt(1, nia); // condición por NIA (usa setInt si es número)

				int filasEliminadas = ps.executeUpdate();

				if (filasEliminadas > 0) {
					System.out.println("El alumno fue eliminado.");
				} else {
					System.out.println("No se encontró ningún alumno con ese NIA.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void borrarPorAp(Scanner sc) {
		System.out.println("Introduce el apellido");
		String apellido = sc.nextLine();

		try (Connection conexion = conexion()) {
			String sql = "DELETE FROM alumno WHERE apellidos LIKE ?";

			try (PreparedStatement ps = conexion.prepareStatement(sql)) {
				ps.setString(1, "%" + apellido + "%");

				int filasEliminadas = ps.executeUpdate();

				if (filasEliminadas > 0) {
					System.out.println(
							"Se eliminaron " + filasEliminadas + " alumnos cuyo apellido contiene '" + apellido + "'.");
				} else {
					System.out.println("No se encontró ningún alumno con ese apellido.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void guardarJson(List<Alumno> alumnos, File fG) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
				.create();

		String jsonOutput = gson.toJson(alumnos);

		try (FileWriter writer = new FileWriter(fG)) {
			writer.write(jsonOutput);

		} catch (IOException e) {
			System.err.println("Error al escribir el fichero JSON: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static List<Alumno> leerJson(File fG) {
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

		try (FileReader reader = new FileReader(fG)) {

			Type listType = new TypeToken<List<Alumno>>() {
			}.getType();

			List<Alumno> alumnos = gson.fromJson(reader, listType);

			return alumnos;

		} catch (Exception e) {
			System.err.println("Error al parsear el contenido JSON:");
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public static void jsonBBDD(File fG) {

		List<Alumno> alumnos = leerJson(fG);

		String sql = "INSERT INTO alumno (nia, nombre, apellidos, ciclo, curso, grupo, genero, fecha)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conexion = conexion(); PreparedStatement ps = conexion.prepareStatement(sql)) {

			for (Alumno a : alumnos) {
				ps.setInt(1, a.getNia());
				ps.setString(2, a.getNombre());
				ps.setString(3, a.getApellidos());
				ps.setString(4, a.getCiclo());
				ps.setString(5, a.getCurso());
				ps.setString(6, a.getGrupo());
				ps.setString(7, String.valueOf(a.getGenero()));
				ps.setDate(8, Date.valueOf(a.getFecha()));

				// insertar
				ps.executeUpdate();
			}
			System.out.println("Alumnos guardados correctamente en la base de datos.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
