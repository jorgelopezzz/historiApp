package dominio;


import com.fasterxml.jackson.databind.*;

import dominio.curso.Curso;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ServicioJSON {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Curso cargarCurso(String rutaArchivo) throws IOException {
        return objectMapper.readValue(new File(rutaArchivo), Curso.class);
    }
}
