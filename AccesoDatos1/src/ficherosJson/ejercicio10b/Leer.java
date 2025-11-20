package ficherosJson.ejercicio10b;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

public class Leer {
	public static void main(String[] args) {

		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

		try (FileReader reader = new FileReader("alumnos.json")) {

			// definir el tipo de dato de destino
			Type listType = new TypeToken<List<Alumno>>() {
			}.getType();

			// pasar el json a la lista
			List<Alumno> alumnos = gson.fromJson(reader, listType);

			if (alumnos != null && !alumnos.isEmpty()) {
				System.out.println("Lectura de JSON completada");

				for (Alumno alumno : alumnos) {
					System.out.println(alumno);
				}
			} else {
				System.out.println("El fichero JSON fue leído, pero no se encontraron alumnos.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
