
package pruebas.prueba03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ejercicio01 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String fichero, fichero2, linea = "";
		int acc = 1;

		System.out.println("Que fichero quieres leer");
		fichero = sc.nextLine();

		System.out.println("En que fichero quieres meter las lineas pares");
		fichero2 = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(fichero));
				PrintWriter pw = new PrintWriter(new FileWriter(fichero2))) {

			while ((linea = br.readLine()) != null) {

				if (acc % 2 == 0) {
					pw.println(linea);
				}

				acc++;
			}

		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
		}

		sc.close();
	}

}
