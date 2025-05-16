package modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import modelo.BloqueContenidos;
import modelo.Curso;
import modelo.MetodoAprendizaje;
import modelo.RealizacionCurso;
import modelo.Usuario;

import java.time.LocalDate;
import java.util.List;

public class RealizacionCursoTest {

    private static List<Arguments> parametrosParaConstructor() {
        // Crear los objetos de prueba
        Curso cursoValido = new Curso("titulo", "descripcion", "rutaImagen", List.of(new BloqueContenidos()));
        Usuario usuarioValido = new Usuario("nombre", "movil", "contrasena", "imagen", "saludo");
        
        // Retornar los argumentos para los tests
        return List.of(
            // Ninguno o un valor nulo
            Arguments.of(cursoValido, usuarioValido, false),
            Arguments.of(cursoValido, null, true),
            Arguments.of(null, usuarioValido, true),

            // Todos los valores nulos
            Arguments.of(null, null, true) 
        );
    }

    @ParameterizedTest
    @MethodSource("parametrosParaConstructor")
    public void testConstructorNulls(Curso cursoParam, Usuario usuarioParam, boolean debeLanzarExcepcion) {
        if (debeLanzarExcepcion) {
            assertThrows(IllegalArgumentException.class, () -> new RealizacionCurso(cursoParam, usuarioParam, MetodoAprendizaje.ESPACIADA));
        } else {
            RealizacionCurso matricula = new RealizacionCurso(cursoParam, usuarioParam, MetodoAprendizaje.ALEATORIA);
            assertEquals(cursoParam, matricula.getCurso());
            assertEquals(usuarioParam, matricula.getUsuario());
        }
    }
}
