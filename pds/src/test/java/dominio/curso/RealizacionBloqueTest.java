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
        	Arguments.of((BloqueContenidos)null)
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
        	Arguments.of(bloqueValido)
        );
    }
	
    @ParameterizedTest
    @MethodSource("argumentosInvalidos")
    void testArgumentosInvalidos(BloqueContenidos bloque) {
        assertThrows(IllegalArgumentException.class, () -> {
            new RealizacionBloque(bloque);
        });
    }
    
    @ParameterizedTest
    @MethodSource("argumentosValidos")
    void testArgumentosValidos(BloqueContenidos bloque) {
        assertDoesNotThrow(() -> { new RealizacionBloque(bloque); });
        assertEquals(bloque, new RealizacionBloque(bloque).getBloque());
    }
    
    @ParameterizedTest
    @MethodSource("argumentosValidos")
    void testAtributosSinCompletar(BloqueContenidos bloque) {
    	RealizacionBloque rb = new RealizacionBloque(bloque);
    	assertTrue(rb.getPuntuacion().isEmpty());
    	assertTrue(rb.getFechaCompletado().isEmpty());
    }
    
    @ParameterizedTest
    @MethodSource("argumentosInvalidosNota")
    void testCompletarIncorrecto(BloqueContenidos bloque, double puntuacion) {
    	RealizacionBloque rb = new RealizacionBloque(bloque);
    	assertThrows(IllegalArgumentException.class, () -> { rb.completar(puntuacion); });
    	
    }
    
    @ParameterizedTest
    @MethodSource("argumentosValidosNota")
    void testCompletarCorrecto(BloqueContenidos bloque, double puntuacion) {
    	RealizacionBloque rb = new RealizacionBloque(bloque);
    	assertDoesNotThrow(() -> { rb.completar(puntuacion); });
    	
    }
    
    

}
