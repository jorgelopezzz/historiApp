package repositorios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.BloqueContenidos;
import dominio.Curso;
import dominio.Tarea;
import dominio.Tip;

class RepositorioCursosTest {

    private RepositorioCursos repo;

    // Datos simulados
    private Curso cursoMock;
    private BloqueContenidos bloqueMock;

    @BeforeEach
    void setUp() {
        // Crear bloque y curso de prueba
        Tarea tarea = new Tip("Hola", "soyese.png");
        bloqueMock = new BloqueContenidos("Bloque de prueba", "Descripción", "imagen.png", List.of(tarea));
        cursoMock = new Curso("Curso de prueba", "Descripción curso", "imagenCurso.png", List.of(bloqueMock));

        // Mock del loader
        ServicioCursoLoader loaderMock = new ServicioCursoLoader() {
            @Override
            public Curso cargarCurso(String rutaArchivo) {
                return cursoMock;
            }
        };

        repo = RepositorioCursos.INSTANCE;

        // Reinicializar (solo una vez o lanzar error si ya lo hiciste)
        try {
            repo.init(loaderMock);
        } catch (IllegalStateException e) {
            // Ya estaba inicializado: lo ignoramos en test repetidos
        }
        repo.anadirCurso(null);
    }

    @Test
    void testGetCursosNoVacio() {
        List<Curso> cursos = repo.getCursos();
        assertNotNull(cursos);
        assertEquals(1, cursos.size());
        assertEquals("Curso de prueba", cursos.get(0).getTitulo());
    }

    @Test
    void testBuscarCursoPorNombreExistente() {
        Curso encontrado = repo.buscarCursoPorNombre("Curso de prueba");
        assertNotNull(encontrado);
        assertEquals("Curso de prueba", encontrado.getTitulo());
    }

    @Test
    void testBuscarCursoPorNombreInexistente() {
        Curso resultado = repo.buscarCursoPorNombre("Curso inexistente");
        assertNull(resultado);
    }

    @Test
    void testAnadirCursoConMock() {
        // Al anadir un curso, siempre se carga el mock
        repo.anadirCurso("archivo/falso.json");

        Curso encontrado = repo.buscarCursoPorNombre("Curso de prueba");
        assertNotNull(encontrado);
        assertEquals("Curso de prueba", encontrado.getTitulo());
    }

    @Test
    void testBuscarBloquePorNombreExistente() {
        Optional<BloqueContenidos> bloqueOpt = repo.buscarBloquePorNombre("Bloque de prueba");
        assertTrue(bloqueOpt.isPresent());
        assertEquals("Bloque de prueba", bloqueOpt.get().getTitulo());
    }

    @Test
    void testBuscarBloquePorNombreInexistente() {
        Optional<BloqueContenidos> bloqueOpt = repo.buscarBloquePorNombre("bloque fantasma");
        assertTrue(bloqueOpt.isEmpty());
    }
}
