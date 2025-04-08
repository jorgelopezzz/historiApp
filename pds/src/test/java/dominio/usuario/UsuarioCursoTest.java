package dominio.usuario;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import dominio.curso.BloqueContenidos;
import dominio.curso.Curso;
import dominio.metodoAprendizaje.MetodoAprendizaje;
import dominio.tarea.Tip;
import gui.emergentes.EmergenteMetodoAprendizaje;

import java.util.List;
import java.util.Optional;

class UsuarioCursoTest {

    private Usuario usuario;
    private Curso curso;
    private MetodoAprendizaje metodo;
    private BloqueContenidos bloque = new BloqueContenidos("titulo", "descripcion", null, List.of(new Tip("enunciado", null)));
    private BloqueContenidos segundoBloque = new BloqueContenidos("titula", "descripcion", null, List.of(new Tip("enunciada", null)));

    @BeforeEach
    void setup() {
        usuario = new Usuario("Ana", "pass", "ana@mail.com", null, "¡Hola!");
        curso = new Curso("titulo", "descripcion", null, List.of(bloque, segundoBloque));
        metodo = MetodoAprendizaje.SECUENCIAL;
    }

    @Test
    void testMatricularCurso() {
        assertFalse(usuario.estaMatriculado(curso.getTitulo()));
        usuario.matricularCurso(curso, metodo);
        assertTrue(usuario.estaMatriculado(curso.getTitulo()));
    }

    @Test
    void testMatricularCursoNoDuplica() {
        assertTrue(usuario.matricularCurso(curso, metodo));
        assertFalse(usuario.matricularCurso(curso, metodo));
        assertEquals(0, usuario.getBloquesCompletados()); 
    }

    @Test
    void testCompletarBloqueCuandoNoMatriculado() {
        assertThrows(IllegalStateException.class, () -> usuario.completarBloque(curso, curso.getBloquesContenidos().get(0), 10.0)); // debería imprimir error, no lanzar excepción
        assertEquals(0, usuario.getBloquesCompletados());
    }

    @Test
    void testCompletarBloqueCuandoMatriculado() {
        usuario.matricularCurso(curso, metodo);
        usuario.completarBloque(curso, bloque, 9.5);
        assertEquals(1, usuario.getBloquesCompletados());
    }

    @Test
    void testMetodoAprendizajeDevueltoCorrectamente() {
        usuario.matricularCurso(curso, metodo);
        Optional<MetodoAprendizaje> metodoObtenido = usuario.getMetodoAprendizaje(curso);
        assertTrue(metodoObtenido.isPresent());
        assertEquals(metodo, metodoObtenido.get());
    }

    @Test
    void testMetodoAprendizajeCuandoNoMatriculado() {
        Optional<MetodoAprendizaje> metodoObtenido = usuario.getMetodoAprendizaje(curso);
        assertTrue(metodoObtenido.isEmpty());
    }

    @Test
    void testHaCompletadoCurso() {
        usuario.matricularCurso(curso, metodo);
        usuario.completarBloque(curso, bloque, 10.0);
        assertFalse(usuario.haCompletado(curso));
        assertEquals(0, usuario.getCursosCompletados());
        usuario.completarBloque(curso, segundoBloque, 10.0);
        assertTrue(usuario.haCompletado(curso));
        assertEquals(1, usuario.getCursosCompletados());
        usuario.matricularCurso(curso, metodo);
        usuario.completarBloque(curso, bloque, 10.0);
        assertTrue(usuario.haCompletado(curso));
    }
    
    @Test
    void testMayorPuntuacion() {
        usuario.matricularCurso(curso, metodo);
        usuario.completarBloque(curso, bloque, 5.0);
        usuario.completarBloque(curso, bloque, 6.5);
        assertEquals(6.5, usuario.getRealizacion(curso.getTitulo()).get().getBloques().get(0).getPuntuacion());
    }
}
