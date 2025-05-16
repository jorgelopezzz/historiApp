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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.fasterxml.jackson.databind.JsonMappingException;

class ServicioJSONTest {

	/* Rutas JSON Tareas */
	
	private static final String rutaTareas = "/tests/tareas.json";
	private static final String rutaEnunciadoInvalido = "/tests/enunciadoInvalido.json";
	private static final String rutaTipSinImagen = "/tests/tipSinImagen.json";
	private static final String rutaVFValido = "/tests/VFValido.json";
	private static final String rutaVFInvalido = "/tests/VFInvalido.json";
	private static final String rutaRellenarInvalido = "/tests/rellenarInvalido.json";
	private static final String rutaTipoTestInvalidoOpciones = "/tests/tipoTestInvalido.json";
	private static final String rutaTipoTestInvalidoIndice = "/tests/tipoTestInvalido1.json";
	
	/* Tests de serialización de tareas */
	
	private final ServicioJSON servicio = new ServicioJSON();
	
	@Test
	void testTarea() {
		String path = ServicioJSON.class.getResource(rutaTareas).getPath(); 
		assertDoesNotThrow(() -> {
			servicio.cargarCurso(path);
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
			servicio.cargarCurso(path);
        });
	}
	
	@Test
	void testVFInvalido() {
		String path = ServicioJSON.class.getResource(rutaVFInvalido).getPath(); 
		assertThrows(JsonMappingException.class, () -> {
			servicio.cargarCurso(path);
        });
	}
	
	@Test
	void testRellenarInvalido() {
		String path = ServicioJSON.class.getResource(rutaRellenarInvalido).getPath(); 
		assertThrows(JsonMappingException.class, () -> {
			servicio.cargarCurso(path);
        });
	}
	
	@Test
	void testTipSinImagen() {
		String path = ServicioJSON.class.getResource(rutaTipSinImagen).getPath(); 
		try {
			Tip t = (Tip) servicio.cargarCurso(path).getBloquesContenidos().get(0).getTareas().get(0);
			assertTrue(t.getRutaImagen().isEmpty());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	@Test
	void testHerenciaPregunta() {
		String path = ServicioJSON.class.getResource(rutaVFValido).getPath(); 
		try {
			List<Tarea> li = servicio.cargarCurso(path).getBloquesContenidos().get(0).getTareas();
			assertTrue(((PreguntaVF)li.get(0)).evaluar(PreguntaVF.CADENA_VERDADERO));
			assertTrue(((PreguntaVF)li.get(1)).evaluar(PreguntaVF.CADENA_FALSO));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	private static List<String> argumentosTipoTestInvalido(){
		return List.of(rutaTipoTestInvalidoIndice, rutaTipoTestInvalidoOpciones);
	}
	
	@ParameterizedTest
	@MethodSource("argumentosTipoTestInvalido")
	void testTipoTestInvalido(String ruta) {
		String path = ServicioJSON.class.getResource(ruta).getPath(); 
		assertThrows(JsonMappingException.class, () -> {
			servicio.cargarCurso(path);
        });
	}
	
	
	
}
