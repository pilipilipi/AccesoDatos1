package ficherosBinario.ejercicio05;

import java.io.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int acc = 0;
		String fichero, ruta;

		System.out.println("Dime el nombre del fichero");
		fichero = sc.nextLine();

		System.out.println("Dime la ruta");
		ruta = sc.nextLine();

		File f = new File(ruta + "\\" + fichero + ".dat");

		try (ObjectOutputStream salidaDatos = new ObjectOutputStream(new FileOutputStream(f))) {
			int seguir;
			
			do {
				Alumno a = leerAlumno(sc);
				salidaDatos.writeObject(a);
				
				acc++;
				
				System.out.println("Para introducir otro alumno introduzca 1, para salir, introduzca 0");
				seguir = leerOpcion(sc);
				
				while(seguir != 1 && seguir != 0) {
					System.out.println("Numero no válido, 1 seguir, 0 finalizar");
					seguir = leerOpcion(sc);
				}
				
			} while (seguir != 0);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try (ObjectInputStream entradaDatos = new ObjectInputStream(new FileInputStream(f))) {
			
			for (int i = 0; i < acc; i++) {
			    Alumno a = (Alumno) entradaDatos.readObject();
			    System.out.println(a);
			}
			//entradaDatos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		sc.close();
	}
	
	public static int leerOpcion(Scanner sc) {
	    int opcion;
	    do {
	        System.out.print("1 para continuar, 0 para salir: ");
	        
	        try {
	            opcion = Integer.parseInt(sc.nextLine());
	            
	        } catch (NumberFormatException e) {
	            opcion = -1;
	        }
	    } while (opcion != 0 && opcion != 1);
	    
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

}
