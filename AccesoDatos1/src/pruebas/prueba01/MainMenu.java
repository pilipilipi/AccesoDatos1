package pruebas.prueba01;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		List<Alumno> alumnos = new ArrayList<>(//Arrays.asList(new Alumno(5555555, "Candela", "Ramirez", "DAW", "Primero", "D2", 'f', "21/09/2004"),
//				new Alumno(2222222, "Fernanda", "Martinez", "DAW", "Segundo", "A2", 'f', "15/06/2003"),
//				new Alumno(1111111, "Javier", "Gonzalez", "DAM", "Primero", "C1", 'm', "03/04/2004"),
//				new Alumno(4444444, "Fran", "Fernandez", "DAM", "Segundo", "C3", 'm', "09/01/2002"),
//				new Alumno(3333333, "Carla", "Lopez", "ASIR", "Primero", "B1", 'f', "28/11/2005"))
				);
		int opcion;

		do {
			opcion = menu(alumnos, sc);
		} while (opcion != 5);

		sc.close();
	}

	public static int menu(List<Alumno> alumnos, Scanner sc) {

		System.out.println("\nElige opcion" 
				+ "\n1. Leer 5 alumnos" 
				+ "\n2. Anadir alumno"
				+ "\n3. Mostrar alumnos por NIA" 
				+ "\n4. Elegir criterio" 
				+ "\n5. Salir\n");
		
		int opcion = Integer.parseInt(sc.nextLine());

		switch (opcion) {

		case 1:
			for (int i = 1; i <= 5; i++) {
				System.out.println("\nAlumno " + i);
				alumnos.add(leerAlumno(sc));
			}
			break;

		case 2:
			alumnos.add(leerAlumno(sc));
			break;

		case 3:
			mostrarPorNia(alumnos);
			break;
			
		case 4:
			menu2(alumnos, sc);
			break;
			
		case 5:
			System.out.println("Terminado");
			break;
			
		default: 
			System.out.println("Opcion no valida\n");
		}
		
		return opcion;
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

	public static void mostrarPorNia(List<Alumno> alumnos) {

		alumnos.sort((a1, a2) -> a1.getNia() - a2.getNia());

		System.out.println("\nAlumnos ordenados por NIA: \n");
		for (Alumno a : alumnos) {
			System.out.println(a);
		}
	}
	
	public static void menu2(List<Alumno> alumnos, Scanner sc) {
		System.out.println("Por que criterio quieres ordenar?"
				+ "\n1. Nombre"
				+ "\n2. Apellidos"
				+ "\n3. Ciclo"
				+ "\n4. Curso"
				+ "\n5. Grupo"
				+ "\n6. Genero"
				+ "\n7. Fecha de nacimiento");
		
		int opcion = Integer.parseInt(sc.nextLine());
		
		switch (opcion) {
		
		case 1:
			alumnos.sort((a1, a2) -> a1.getNombre().compareTo(a2.getNombre()));
			break;
			
		case 2:
			alumnos.sort((a1, a2) -> a1.getApellidos().compareTo(a2.getApellidos()));
			break;
			
		case 3:
			alumnos.sort((a1, a2) -> a1.getCiclo().compareTo(a2.getCiclo()));
			break;
			
		case 4:
			alumnos.sort((a1, a2) -> a1.getCurso().compareTo(a2.getCurso()));
			break;
			
		case 5:
			alumnos.sort((a1, a2) -> a1.getGrupo().compareTo(a2.getGrupo()));
			break;
			
		case 6:
			alumnos.sort((a1, a2) -> Character.compare(a1.getGenero(), a2.getGenero()));
			break;
			
		case 7:
			alumnos.sort((a1, a2) -> a1.getFecha().compareTo(a2.getFecha()));
			break;
		
		default:
			System.out.println("Opcion no válida\n");
			break;
		}
		for(Alumno alumno: alumnos) {
			System.out.println(alumno);
		}
	}	
}



















