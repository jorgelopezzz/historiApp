package dominio.curso;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import dominio.tarea.PreguntaVF;
import dominio.tarea.Tip;

class RealizacionBloqueTest {

	private static BloqueContenidos bloqueValido = new BloqueContenidos("Titulo", "Descripcion", null, List.of(new Tip("Enunciado", null)));
	static List<Arguments> argumentosInvalidos() {
        return List.of(
        	Arguments.of((BloqueContenidos)null, 1.0)
        );
    }
	
	static List<Arguments> argumentosInvalidosNota() {
		return List.of(
        		Arguments.of(bloqueValido, -1.4),
        		Arguments.of(bloqueValido, 23121.4),
        		Arguments.of(bloqueValido, Double.MAX_VALUE),
        		Arguments.of(bloqueValido, -1*Double.MIN_VALUE)
        );
    }
	
	static List<Arguments> argumentosValidosNota() {
		return List.of(
        		Arguments.of(bloqueValido, 1.4),
        		Arguments.of(bloqueValido, 0.0),
        		Arguments.of(bloqueValido, 10.0),
        		Arguments.of(bloqueValido, Double.MIN_VALUE)
        );
    }
	
	static List<Arguments> argumentosValidos() {
        return List.of(
        	Arguments.of(bloqueValido, 3.0),
        	Arguments.of(bloqueValido, 0.0),
        	Arguments.of(bloqueValido, 10.0)
        );
    }
	
    @ParameterizedTest
    @MethodSource("argumentosInvalidos")
    void testArgumentosInvalidos(BloqueContenidos bloque, double puntuacion) {
        assertThrows(IllegalArgumentException.class, () -> {
            new RealizacionBloque(bloque, puntuacion);
        });
    }
    
    @ParameterizedTest
    @MethodSource("argumentosValidos")
    void testArgumentosValidos(BloqueContenidos bloque, double puntuacion) {
        assertDoesNotThrow(() -> { new RealizacionBloque(bloque, puntuacion); });
        assertEquals(bloque, new RealizacionBloque(bloque, puntuacion).getBloque());
        assertEquals(puntuacion, new RealizacionBloque(bloque, puntuacion).getPuntuacion());
    }
    
    @ParameterizedTest
    @MethodSource("argumentosInvalidosNota")
    void testCompletarIncorrecto(BloqueContenidos bloque, double puntuacion) {
    	assertThrows(IllegalArgumentException.class, () -> new RealizacionBloque(bloque, puntuacion));
    }
    
    @ParameterizedTest
    @MethodSource("argumentosValidosNota")
    void testCompletarCorrecto(BloqueContenidos bloque, double puntuacion) {
    	assertDoesNotThrow(() -> new RealizacionBloque(bloque, puntuacion));
    	
    }
    
    

}
