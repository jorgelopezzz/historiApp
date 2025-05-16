package dominio;

import com.fasterxml.jackson.databind.*;

import repositorios.ServicioCursoLoader;

import java.io.File;
import java.io.IOException;

public class ServicioJSON implements ServicioCursoLoader{
	
    private final ObjectMapper objectMapper;
    
    public ServicioJSON() {
    	objectMapper = new ObjectMapper();
    	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    public Curso cargarCurso(String rutaArchivo) throws IOException {
        return objectMapper.readValue(new File(rutaArchivo), Curso.class);
    }
}
