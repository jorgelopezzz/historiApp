package dominio;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class ServicioJSONTest {

	/* Rutas a JSON */
	private static final String rutaTareas = "/tests/tareas.json";
	
	
	@Test
	void testTarea() {
		String path = ServicioJSON.class.getResource(rutaTareas).getPath(); 
		assertDoesNotThrow(() -> {
			ServicioJSON.INSTANCE.cargarCurso(path);
        });
	}
}
