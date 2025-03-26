package dominio;


import com.fasterxml.jackson.databind.*;

import dominio.curso.Curso;

import java.io.File;
import java.io.IOException;

public enum ServicioJSON {
	INSTANCE;
	
    private ObjectMapper objectMapper;
    
    private ServicioJSON() {
    	objectMapper = new ObjectMapper();
    	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    public Curso cargarCurso(String rutaArchivo) throws IOException {
        return objectMapper.readValue(new File(rutaArchivo), Curso.class);
    }
}
