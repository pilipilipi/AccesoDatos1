package pruebas.prueba01;

import java.util.*;
import java.util.Arrays;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		List<Alumno> alumnos = new ArrayList<>(
				Arrays.asList(new Alumno(5555555, "Candela", "Ramirez", "DAW", "Primero", "D2", 'f', "21/09/2004"),
						new Alumno(2222222, "Fernanda", "Martinez", "DAW", "Segundo", "A2", 'f', "15/06/2003"),
						new Alumno(1111111, "Javier", "Gonzalez", "DAM", "Primero", "C1", 'm', "03/04/2004"),
						new Alumno(4444444, "Fran", "Fernandez", "DAM", "Segundo", "C3", 'm', "09/01/2002"),
						new Alumno(3333333, "Carla", "Lopez", "ASIR", "Primero", "B1", 'f', "28/11/2005")));

		for (Alumno a : alumnos) {
			System.out.println(a);
		}

//		Comparator<Alumno> compararNia = new Comparator<Alumno>() {
//			@Override
//			public int compare(Alumno a1, Alumno a2) {
//				return a1.getNia() - a2.getNia();
//			}
//		};

		alumnos.sort((a1, a2) -> a1.getNia() - a2.getNia());

		System.out.println("\n\nAlumnos ordenados por NIA: \n");
		for (Alumno a : alumnos) {
			System.out.println(a);
		}
		
		
	}

}

