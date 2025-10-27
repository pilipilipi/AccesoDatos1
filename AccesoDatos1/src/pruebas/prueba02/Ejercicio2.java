package pruebas.prueba02;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ejercicio2 {
	final static String EXIT = "exit";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		try (PrintWriter pw = new PrintWriter(new FileWriter("ejercicio2.txt"))) {

			System.out.println("Escribe lineas, exit para acabar");
			String linea = "";

			while (!sc.nextLine().equalsIgnoreCase(EXIT)) {
				pw.println(linea);
			}

		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
		}

		sc.close();
	}
}
