package pruebas.prueba02;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ejercicio2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try (PrintWriter pw = new PrintWriter(new FileWriter("ejercicio2.txt"))) {

			System.out.println("Escribe lineas, exit para acabar");
			String linea = "";

			while (!linea.equalsIgnoreCase("exit")) {
				linea = sc.nextLine();

				if (!linea.equalsIgnoreCase("exit")) {
					pw.println(linea);
				}
			}

		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
		}

		sc.close();
	}
}
