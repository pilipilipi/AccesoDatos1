package ficherosJson.ejercicio10b;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Este adaptador es esencial para serializar y deserializar LocalDate con Gson
// sin generar errores de reflexión en Java 9+.

public class LocalDateAdapter extends TypeAdapter<LocalDate> {
    
    // Usamos el formato ISO estándar (yyyy-MM-dd)
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            // Escribe el LocalDate como una cadena (String)
            out.value(value.format(FORMATTER));
        }
    }

    @Override
    public LocalDate read(JsonReader in) throws IOException {
        if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
            in.nextNull();
            return null;
        } else {
            // Lee la cadena (String) y la convierte en un LocalDate
            return LocalDate.parse(in.nextString(), FORMATTER);
        }
    }
}
