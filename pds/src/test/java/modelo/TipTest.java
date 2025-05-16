package modelo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TipTest {
	static List<Arguments> argumentosInvalidos() {
		String rutaImagen = "unaRuta";
        return List.of(
        	Arguments.of((Object)null, rutaImagen),
        	Arguments.of("", rutaImagen)
        );
    }

    @ParameterizedTest
    @MethodSource("argumentosInvalidos")
    void testArgumentosInvalidos(String enunciado, String rutaImagen) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Tip(enunciado, rutaImagen);
        });
    }
    
	static List<Arguments> argumentosValidos() {
		return List.of( 
				Arguments.of("Murcia es la capital de la región de Murcia", (Object)null),
				Arguments.of("Naipyidó es la capital de Myanmar", "unaRuta"));
    }
	
    @ParameterizedTest
    @MethodSource("argumentosValidos")
    void testArgumentosValidos(String enunciado, String rutaImagen) {
        assertDoesNotThrow(() -> new Tip(enunciado, rutaImagen));
    }
    
    @Test
    void testImagen() {
        String enunciado = "Murcia es una ciudad calurosa."; 
    	Tip t = new Tip(enunciado, null);
        assertTrue(t.getRutaImagen().isEmpty());
    }

}
