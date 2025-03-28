package dominio;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonMappingException;

import dominio.curso.Curso;
import dominio.tarea.PreguntaVF;
import dominio.tarea.Tarea;
import dominio.tarea.Tip;

class ServicioJSONTest {

	/* Rutas a JSON */
	private static final String rutaTareas = "/tests/tareas.json";
	private static final String rutaEnunciadoInvalido = "/tests/enunciadoInvalido.json";
	private static final String rutaTipSinImagen = "/tests/tipSinImagen.json";
	private static final String rutaVFValido = "/tests/VFValido.json";
	private static final String rutaVFInvalido = "/tests/VFInvalido.json";
	private static final String rutaRellenarInvalido = "/tests/rellenarInvalido.json";
	
	
	@Test
	void testTarea() {
		String path = ServicioJSON.class.getResource(rutaTareas).getPath(); 
		assertDoesNotThrow(() -> {
			ServicioJSON.INSTANCE.cargarCurso(path);
        });
	}
	
	/* Importante:
	 * Algunos tests lanzan una IllegalArgumentException que se verá por 
	 * la salida de error. Esta contemplada y es lo que provoca que
	 * la librería Jackson lance la JsonMappingException.
	 */
	@Test
	void testTipInvalido() {
		String path = ServicioJSON.class.getResource(rutaEnunciadoInvalido).getPath(); 
		assertThrows(JsonMappingException.class, () -> {
			ServicioJSON.INSTANCE.cargarCurso(path);
        });
	}
	
	@Test
	void testVFInvalido() {
		String path = ServicioJSON.class.getResource(rutaVFInvalido).getPath(); 
		assertThrows(JsonMappingException.class, () -> {
			ServicioJSON.INSTANCE.cargarCurso(path);
        });
	}
	
	@Test
	void testRellenarInvalido() {
		String path = ServicioJSON.class.getResource(rutaRellenarInvalido).getPath(); 
		assertThrows(JsonMappingException.class, () -> {
			ServicioJSON.INSTANCE.cargarCurso(path);
        });
	}
	
	@Test
	void testTipSinImagen() {
		String path = ServicioJSON.class.getResource(rutaTipSinImagen).getPath(); 
		try {
			Tip t = (Tip) ServicioJSON.INSTANCE.cargarCurso(path).getBloquesContenidos().get(0).getTareas().get(0);
			assertTrue(t.getRutaImagen().isEmpty());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	@Test
	void testHerenciaPregunta() {
		String path = ServicioJSON.class.getResource(rutaVFValido).getPath(); 
		try {
			List<Tarea> li = ServicioJSON.INSTANCE.cargarCurso(path).getBloquesContenidos().get(0).getTareas();
			assertTrue(((PreguntaVF)li.get(0)).evaluar(PreguntaVF.CADENA_VERDADERO));
			assertFalse(((PreguntaVF)li.get(1)).evaluar(PreguntaVF.CADENA_FALSO));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	
	
}
