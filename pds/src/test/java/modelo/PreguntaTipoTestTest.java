package modelo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PreguntaTipoTestTest {

	static List<Arguments> argumentosInvalidos() {
		String enunciadoValido = "País donde se originaron las baguettes";
		List<String> opcionesValidas = List.of("España", "Francia", "Italia");
		int correctaValida = 1;
        return List.of(
        	Arguments.of(null, opcionesValidas, correctaValida),
        	Arguments.of("", opcionesValidas, correctaValida),
        	Arguments.of(enunciadoValido, null, 0),
        	Arguments.of(enunciadoValido, List.of(), 0),
        	Arguments.of(enunciadoValido, opcionesValidas, correctaValida - opcionesValidas.size() - 10),
        	Arguments.of(enunciadoValido, opcionesValidas, opcionesValidas.size() + 10)
        );
    }

    @ParameterizedTest
    @MethodSource("argumentosInvalidos")
    void testArgumentosInvalidos(String enunciado, List<String> opciones, int correcta) {
        assertThrows(IllegalArgumentException.class, () -> {
            new PreguntaTipoTest(enunciado, opciones, correcta);
        });
    }
    
	static List<Arguments> argumentosValidos() {
		List<String> enunciados = List.of("Capital de la región de Murcia", "Capital de Myanmar");
		List<List<String>> respuestas = List.of(List.of("Murcia", "Cartagena", "Lorca"),
				List.of("Albudeite", "Ulan Batór", "Naipyidó"));
		List<Integer> correctas = List.of(0,2);
        return IntStream.range(0, 2)
        		.mapToObj( i -> Arguments.of(enunciados.get(i), respuestas.get(i), correctas.get(i)))
        		.collect(Collectors.toList());
    }
	
    @ParameterizedTest
    @MethodSource("argumentosValidos")
    void testArgumentosValidos(String enunciado, List<String> opciones, int correcta) {
        assertDoesNotThrow(() -> new PreguntaTipoTest(enunciado, opciones, correcta));
    }
    
    @ParameterizedTest
    @MethodSource("argumentosValidos")
    void testEvaluar(String enunciado, List<String> opciones, int correcta) {
        PreguntaTipoTest p = new PreguntaTipoTest(enunciado, opciones, correcta);
    	assertTrue(p.evaluar(opciones.get(correcta)));
    	assertFalse(p.evaluar(opciones.get(correcta) + "..."));
    	assertFalse(p.evaluar(null));
    	assertFalse(p.evaluar(""));
    }


}
