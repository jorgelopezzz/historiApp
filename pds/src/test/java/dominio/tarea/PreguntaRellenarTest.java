package dominio.tarea;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PreguntaRellenarTest {

	static List<Arguments> argumentosInvalidos() {
		String respuestaValida = "respuesta";
		String enunciadoValido = "enunciado";
        return List.of(
        	Arguments.of(null, respuestaValida),
        	Arguments.of("", respuestaValida),
            Arguments.of(enunciadoValido, null),
            Arguments.of(enunciadoValido, "")
        );
    }

    @ParameterizedTest
    @MethodSource("argumentosInvalidos")
    void testArgumentosInvalidos(String enunciado, String respuesta) {
        assertThrows(IllegalArgumentException.class, () -> {
            new PreguntaRellenar(enunciado, respuesta);
        });
    }
    
	static List<Arguments> argumentosValidos() {
		List<String> enunciados = List.of("Capital de la región de Murcia", "Capital de Myanmar");
		List<String> respuestas = List.of("Murcia", "Naipyidó");
        return IntStream.range(0, 2)
        		.mapToObj( i -> Arguments.of(enunciados.get(i), respuestas.get(i)))
        		.collect(Collectors.toList());
    }
	
    @ParameterizedTest
    @MethodSource("argumentosValidos")
    void testArgumentosValidos(String enunciado, String respuesta) {
        assertDoesNotThrow(() -> new PreguntaRellenar(enunciado, respuesta));
    }

    @ParameterizedTest
    @MethodSource("argumentosValidos")
    void testEvaluar(String enunciado, String respuesta) {
        PreguntaRellenar p = new PreguntaRellenar(enunciado, respuesta);
        assertTrue(p.evaluar(p.getRespuesta()));
        assertFalse(p.evaluar(p.getRespuesta() + "..."));
    }

}
