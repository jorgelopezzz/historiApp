package dominio.tarea;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TipTest {
	static List<Arguments> argumentosInvalidos() {
		String rutaImagen = "unaRuta";
        return List.of(
        	Arguments.of((Object)null, Optional.of(rutaImagen)),
        	Arguments.of("", Optional.of(rutaImagen)),
        	Arguments.of("Enunciado", null)
        );
    }

    @ParameterizedTest
    @MethodSource("argumentosInvalidos")
    void testArgumentosInvalidos(String enunciado, Optional<String> rutaImagen) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Tip(enunciado, rutaImagen);
        });
    }
    
	static List<Arguments> argumentosValidos() {
		List<String> enunciados = List.of("Murcia es la capital de la región de Murcia", "Naipyidó es la capital de Myanmar");
		List<Optional<String>> rutas = List.of(Optional.empty(), Optional.of("unaRuta"));
        return IntStream.range(0, 2)
        		.mapToObj( i -> Arguments.of(enunciados.get(i), rutas.get(i)))
        		.collect(Collectors.toList());
    }
	
    @ParameterizedTest
    @MethodSource("argumentosValidos")
    void testArgumentosValidos(String enunciado, Optional<String> rutaImagen) {
        assertDoesNotThrow(() -> new Tip(enunciado, rutaImagen));
    }
    
    @Test
    void testImagen() {
        String enunciado = "Murcia es una ciudad calurosa."; 
    	Tip t = new Tip(enunciado, Optional.empty());
        assertTrue(t.getRutaImagen().isEmpty());
    }

}
