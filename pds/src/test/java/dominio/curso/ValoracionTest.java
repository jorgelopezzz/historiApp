package dominio.curso;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import modelo.Curso;
import modelo.Usuario;
import modelo.Valoracion;

class ValoracionTest {

	static List<Arguments> argumentosValidos() {
        return List.of(
        	Arguments.of(new Curso(), new Usuario(), 1, "a"),
        	Arguments.of(new Curso(), new Usuario(), 10, "b")
        );
    }
	
	static List<Arguments> argumentosInvalidos() {
        return List.of(
        	Arguments.of((Curso)null, new Usuario(), 1, "a"),
        	Arguments.of(new Curso(), (Usuario)null, 10, "b"),
        	Arguments.of(new Curso(), new Usuario(), 11, "b"),
        	Arguments.of(new Curso(), new Usuario(), 10, null)
        );
    }
	
    @ParameterizedTest
    @MethodSource("argumentosInvalidos")
    void testArgumentosInvalidos(Curso c, Usuario u, int n, String com) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Valoracion(c, u, n, com);
        });
    }
	
    @Test
    void constructorVacio() {
    	assertDoesNotThrow( () -> new Valoracion());
    }
    
    @ParameterizedTest
    @MethodSource("argumentosValidos")
    void testArgumentosValidos(Curso c, Usuario u, int n, String com) {
        assertDoesNotThrow(() -> { new Valoracion(c, u, n, com); });
        Valoracion v = new Valoracion(c, u, n, com);
        assertEquals(c.getTitulo(), v.getCursoNombre());
        assertEquals(u, v.getUsuario());
        assertEquals(com, v.getComentario());
        assertEquals(n, v.getPuntuacion());
    }
    
	
}
