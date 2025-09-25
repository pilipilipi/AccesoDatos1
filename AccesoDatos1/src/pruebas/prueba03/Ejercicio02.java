package pruebas.prueba03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ejercicio02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String fichero, fichero2, fichero3, linea, linea2;

		System.out.println("Que fichero quieres leer");
		fichero = sc.nextLine();

		System.out.println("Que otro fichero quieres leer");
		fichero2 = sc.nextLine();

		System.out.println("En que fichero quieres meter las lineas mezcladas");
		fichero3 = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(fichero));
				BufferedReader br2 = new BufferedReader(new FileReader(fichero2));
				PrintWriter pw = new PrintWriter(new FileWriter(fichero3))) {

			while ((linea = br.readLine()) != null && (linea2 = br2.readLine()) != null) {

				pw.println(linea);

				pw.println(linea2);
			}

		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
		}

		sc.close();
	}

}
