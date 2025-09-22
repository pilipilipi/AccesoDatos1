package pruebas.prueba02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String archivo;
		System.out.println("Dime el nombre del archivo");
		archivo = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

			String linea;
			
			while ((linea = br.readLine()) != null) {

				System.out.println(linea);
			}

		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
		}
		
		sc.close();
	}
}