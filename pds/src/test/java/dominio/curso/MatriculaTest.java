package dominio.curso;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import dominio.usuario.Usuario;
import java.util.List;

public class MatriculaTest {

    private static List<Arguments> parametrosParaConstructor() {
        // Crear los objetos de prueba
        Curso cursoValido = new Curso("titulo", "descripcion", "rutaImagen", List.of(new BloqueContenidos()));
        Usuario usuarioValido = new Usuario("nombre", "movil", "contrasena", "imagen", "saludo", null);
        
        // Retornar los argumentos para los tests
        return List.of(
            // Ninguno o un valor nulo
            Arguments.of(cursoValido, usuarioValido, LocalDate.now(), false),
            Arguments.of(cursoValido, null, LocalDate.now(), true),
            Arguments.of(null, usuarioValido, LocalDate.now(), true),
            Arguments.of(cursoValido, usuarioValido, null, true),

            // Todos los valores nulos
            Arguments.of(null, null, null, true) 
        );
    }

    @ParameterizedTest
    @MethodSource("parametrosParaConstructor")
    public void testConstructorNulls(Curso cursoParam, Usuario usuarioParam, LocalDate fechaMatriculaParam, boolean debeLanzarExcepcion) {
        if (debeLanzarExcepcion) {
            assertThrows(IllegalArgumentException.class, () -> new RealizacionCurso(cursoParam, usuarioParam, fechaMatriculaParam));
        } else {
            RealizacionCurso matricula = new RealizacionCurso(cursoParam, usuarioParam, fechaMatriculaParam);
            assertEquals(cursoParam, matricula.getCurso());
            assertEquals(usuarioParam, matricula.getUsuario());
            assertEquals(fechaMatriculaParam, matricula.fechaMatricula());
        }
    }
}
