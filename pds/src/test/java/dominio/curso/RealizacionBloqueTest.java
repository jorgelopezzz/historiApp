package dominio.curso;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import dominio.tarea.PreguntaVF;
import dominio.tarea.Tip;

class RealizacionBloqueTest {

	private static RealizacionCurso rc = new RealizacionCurso();
	
	private static BloqueContenidos bloqueValido = new BloqueContenidos("Titulo", "Descripcion", null, List.of(new Tip("Enunciado", null)));
	static List<Arguments> argumentosInvalidos() {
        return List.of(
        	Arguments.of((BloqueContenidos)null, 1.0, rc)
        );
    }
	
	static List<Arguments> argumentosInvalidosNota() {
		return List.of(
        		Arguments.of(bloqueValido, -1.4, rc),
        		Arguments.of(bloqueValido, 23121.4, rc),
        		Arguments.of(bloqueValido, Double.MAX_VALUE, rc),
        		Arguments.of(bloqueValido, -1*Double.MIN_VALUE, rc)
        );
    }
	
	static List<Arguments> argumentosValidosNota() {
		return List.of(
        		Arguments.of(bloqueValido, 1.4, rc),
        		Arguments.of(bloqueValido, 0.0, rc),
        		Arguments.of(bloqueValido, 10.0, rc),
        		Arguments.of(bloqueValido, Double.MIN_VALUE, rc)
        );
    }
	
	static List<Arguments> argumentosValidos() {
		
        return List.of(
        	Arguments.of(bloqueValido, 3.0, rc),
        	Arguments.of(bloqueValido, 0.0, rc),
        	Arguments.of(bloqueValido, 10.0, rc)
        );
    }
	
    @ParameterizedTest
    @MethodSource("argumentosInvalidos")
    void testArgumentosInvalidos(BloqueContenidos bloque, double puntuacion, RealizacionCurso realizacionCurso) {
        assertThrows(IllegalArgumentException.class, () -> {
            new RealizacionBloque(realizacionCurso, bloque, puntuacion);
        });
    }
    
    @ParameterizedTest
    @MethodSource("argumentosValidos")
    void testArgumentosValidos(BloqueContenidos bloque, double puntuacion, RealizacionCurso realizacionCurso) {
        assertDoesNotThrow(() -> { new RealizacionBloque(realizacionCurso, bloque, puntuacion); });
        assertEquals(bloque, new RealizacionBloque(realizacionCurso, bloque, puntuacion).getBloque());
        assertEquals(puntuacion, new RealizacionBloque(realizacionCurso, bloque, puntuacion).getPuntuacion());
    }
    
    @ParameterizedTest
    @MethodSource("argumentosInvalidosNota")
    void testCompletarIncorrecto(BloqueContenidos bloque, double puntuacion, RealizacionCurso realizacionCurso) {
    	assertThrows(IllegalArgumentException.class, () -> new RealizacionBloque(realizacionCurso, bloque, puntuacion));
    }
    
    @ParameterizedTest
    @MethodSource("argumentosValidosNota")
    void testCompletarCorrecto(BloqueContenidos bloque, double puntuacion, RealizacionCurso realizacionCurso) {
    	assertDoesNotThrow(() -> new RealizacionBloque(realizacionCurso, bloque, puntuacion));
    	
    }
    
    @Test
    void testFechaCompletadoEsMismoDiaActual() {
        // Given
        double puntuacion = 8.5;
        
        // When
        RealizacionBloque realizacion = new RealizacionBloque(rc, bloqueValido, puntuacion);
        
        // Then
        LocalDateTime fechaCompletado = realizacion.getFechaCompletado();
        LocalDateTime ahora = LocalDateTime.now();
        
        // Comparar año, mes y día
        assertEquals(ahora.getYear(), fechaCompletado.getYear(), 
                    "El año de la fecha de completado debe ser el actual");
        assertEquals(ahora.getMonth(), fechaCompletado.getMonth(), 
                    "El mes de la fecha de completado debe ser el actual");
        assertEquals(ahora.getDayOfMonth(), fechaCompletado.getDayOfMonth(), 
                    "El día de la fecha de completado debe ser el actual");
    }
    
    

}
