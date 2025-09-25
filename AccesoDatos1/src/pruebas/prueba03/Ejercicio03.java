package pruebas.prueba03;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ejercicio03 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String fichero;

		System.out.println("En que fichero quierses escribir");
		fichero = sc.nextLine();

		try (PrintWriter pw = new PrintWriter(new FileWriter(fichero, true))) {

			System.out.println("Escribe lineas, salir para acabar");
			String linea;

			do {
				linea = sc.nextLine();

				if (!linea.equalsIgnoreCase("salir")) {
					pw.println(linea);
				}
			} while (!linea.equalsIgnoreCase("salir"));

		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
		}

		sc.close();
	}

}
