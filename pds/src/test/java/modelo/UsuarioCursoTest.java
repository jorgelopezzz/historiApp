package modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

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
        usuario = new Usuario("Ana", "pass", "ana@mail.com", "/perfil.png", "¡Hola!");
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
        assertThrows(IllegalStateException.class, () -> usuario.completarBloque(curso, curso.getBloquesContenidos().get(0), 10));
        assertEquals(0, usuario.getBloquesCompletados());
    }

    @Test
    void testCompletarBloqueCuandoMatriculado() {
        usuario.matricularCurso(curso, metodo);
        usuario.completarBloque(curso, bloque, 9);
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
        usuario.completarBloque(curso, bloque, 10);
        assertFalse(usuario.haCompletado(curso.getTitulo()));
        assertEquals(0, usuario.getCursosCompletados());
        usuario.completarBloque(curso, segundoBloque, 10);
        assertTrue(usuario.haCompletado(curso.getTitulo()));
        assertEquals(1, usuario.getCursosCompletados());
        usuario.matricularCurso(curso, metodo);
        usuario.completarBloque(curso, bloque, 10);
        assertTrue(usuario.haCompletado(curso.getTitulo()));
    }
    
    @Test
    void testMayorPuntuacion() {
        usuario.matricularCurso(curso, metodo);
        usuario.completarBloque(curso, bloque, 5);
        usuario.completarBloque(curso, bloque, 6);
        assertEquals(6, usuario.getRealizacion(curso.getTitulo()).get().getBloques().get(0).getPuntuacion());
    }

    @Test
    void testBloqueCompletadoDetectaCorrectamente() {
        usuario.matricularCurso(curso, metodo);
        usuario.completarBloque(curso, bloque, 8);

        assertTrue(usuario.bloqueCompletado(curso.getTitulo(), bloque.getTitulo()));
        assertFalse(usuario.bloqueCompletado(curso.getTitulo(), segundoBloque.getTitulo()));
    }

    @Test
    void testHacerValoracionYObtenerValoraciones() {
        assertTrue(usuario.hacerValoracion(curso, "Muy bueno", 9));

        var valoraciones = usuario.getValoraciones();
        assertEquals(1, valoraciones.size());

        var v = valoraciones.get(0);
        assertEquals("Muy bueno", v.getComentario());
        assertEquals("titulo", v.getCursoNombre());
        assertEquals(9, v.getPuntuacion());
    }

    @Test
    void testGetValoracionesPorCursoNombre() {
        usuario.hacerValoracion(curso, "Valoración 1", 8);
        usuario.hacerValoracion(curso, "Valoración 2", 9);

        var valoracionesCurso = usuario.getValoracionesPorCursoNombre(curso.getTitulo());

        assertEquals(2, valoracionesCurso.size());
        assertTrue(valoracionesCurso.stream().allMatch(v -> v.getCursoNombre().equals(curso.getTitulo())));
    }

    @Test
    void testGetPuntuacionAcumulaCorrectamente() {
        usuario.matricularCurso(curso, metodo);
        usuario.completarBloque(curso, bloque, 7);
        usuario.completarBloque(curso, segundoBloque, 8);

        assertEquals(15, usuario.getPuntuacion());
    }

}
