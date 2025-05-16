package dominio.tarea;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import dominio.PreguntaVF;

class PreguntaVFTest {

	static List<Arguments> argumentosInvalidos() {
        return List.of(
        	Arguments.of(null, true),
        	Arguments.of("", true)
        );
    }

    @ParameterizedTest
    @MethodSource("argumentosInvalidos")
    void testArgumentosInvalidos(String enunciado, boolean esVerdadero) {
        assertThrows(IllegalArgumentException.class, () -> {
            new PreguntaVF(enunciado, esVerdadero);
        });
    }
    
	static List<Arguments> argumentosValidos() {
		List<String> enunciados = List.of("Murcia es la capital de la región de Murcia", "Albudeite es la capital de Myanmar");
		List<Boolean> respuestas = List.of(true, false);
        return IntStream.range(0, 2)
        		.mapToObj( i -> Arguments.of(enunciados.get(i), respuestas.get(i)))
        		.collect(Collectors.toList());
    }
	
    @ParameterizedTest
    @MethodSource("argumentosValidos")
    void testArgumentosValidos(String enunciado, boolean esVerdadero) {
        assertDoesNotThrow(() -> new PreguntaVF(enunciado, esVerdadero));
    }
    
    @ParameterizedTest
    @MethodSource("argumentosValidos")
    void testEnunciado(String enunciado, boolean esVerdadero) {
        PreguntaVF p = new PreguntaVF(enunciado, esVerdadero); 
    	assertNotEquals(p.getEnunciado(), null);
    	assertFalse(p.getEnunciado().length() == 0);
    }
    
    @ParameterizedTest
    @MethodSource("argumentosValidos")
    void testEvaluar(String enunciado, boolean esVerdadero) {
        PreguntaVF p = new PreguntaVF(enunciado, esVerdadero);
    	assertTrue(p.evaluar( esVerdadero ? PreguntaVF.CADENA_VERDADERO : PreguntaVF.CADENA_FALSO ));
    	assertFalse(p.evaluar( esVerdadero ? PreguntaVF.CADENA_FALSO : PreguntaVF.CADENA_VERDADERO ));
    	assertFalse(p.evaluar(null));
    	assertFalse(p.evaluar(""));
    }

}
