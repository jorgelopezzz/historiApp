package dominio.curso;


import dominio.ServicioJSON;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

public class RepositorioCursosTest {

    private ServicioJSON servicioMock;

    @BeforeEach
    public void setUp() {
        // Mock de ServicioJSON
        servicioMock = mock(ServicioJSON.class);

        // Mock del comportamiento de carga de cursos
        Curso curso1 = new Curso("Curso 1", "Descripción 1", "rutaImagen1", null);
        Curso curso2 = new Curso("Curso 2", "Descripción 2", "rutaImagen2", null);
        try {			
        	when(servicioMock.cargarCurso("resources/curso1.json")).thenReturn(curso1);
        	when(servicioMock.cargarCurso("resources/curso2.json")).thenReturn(curso2);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
    }

    @Test
    public void testRepositorioCursosCargaCursosCorrectamente() {
        // Simulamos la carpeta y los archivos con los métodos adecuados
        File carpetaMock = mock(File.class);
        File archivoMock1 = mock(File.class);
        File archivoMock2 = mock(File.class);

        when(carpetaMock.exists()).thenReturn(true);
        when(carpetaMock.isDirectory()).thenReturn(true);
        when(carpetaMock.listFiles()).thenReturn(new File[]{archivoMock1, archivoMock2});

        when(archivoMock1.getPath()).thenReturn("resources/curso1.json");
        when(archivoMock2.getPath()).thenReturn("resources/curso2.json");

        // Simulamos que los cursos se cargan correctamente
        RepositorioCursos repositorio = RepositorioCursos.INSTANCE;
        List<Curso> cursos = repositorio.getCursos();

        assertNotNull(cursos, "La lista de cursos no puede ser nula");
        assertEquals(2, cursos.size(), "Se deben cargar 2 cursos");

        assertEquals("Curso 1", cursos.get(0).getTitulo());
        assertEquals("Curso 2", cursos.get(1).getTitulo());
    }

    @Test
    public void testRepositorioCursosConArchivosInvalidos() {
        // Simulamos que un archivo JSON lanza una excepción
        try {			
        	when(servicioMock.cargarCurso("resources/cursoInvalido.json")).thenThrow(new RuntimeException("Error al cargar el curso"));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

        RepositorioCursos repositorio = RepositorioCursos.INSTANCE;
        List<Curso> cursos = repositorio.getCursos();

        assertTrue(cursos.isEmpty(), "La lista de cursos debería estar vacía");
    }

    @Test
    public void testRepositorioCursosConNingunArchivo() {
        // Simulamos que no hay archivos .json en la carpeta
        File carpetaMock = mock(File.class);
        when(carpetaMock.exists()).thenReturn(true);
        when(carpetaMock.isDirectory()).thenReturn(true);
        when(carpetaMock.listFiles()).thenReturn(new File[]{});

        RepositorioCursos repositorio = RepositorioCursos.INSTANCE;
        List<Curso> cursos = repositorio.getCursos();

        assertTrue(cursos.isEmpty(), "La lista de cursos debería estar vacía cuando no hay archivos .json");
    }
}
