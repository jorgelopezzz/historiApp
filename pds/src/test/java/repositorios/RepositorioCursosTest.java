package repositorios;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.curso.BloqueContenidos;
import dominio.curso.Curso;

class RepositorioCursosTest {
	
	private RepositorioCursos repo;
	
	@BeforeEach
	void setUp() {
		repo = RepositorioCursos.INSTANCE;
	}

    @Test
    void testGetCursosNoVacio() {
        List<Curso> cursos = repo.getCursos();
        assertNotNull(cursos, "La lista de cursos no debería ser null");
        assertFalse(cursos.isEmpty(), "La lista de cursos no debería estar vacía si hay archivos en resources/cursos");
    }
    
    @Test
    void testBuscarCursoPorNombreExistente() {
        List<Curso> cursos = repo.getCursos();
        assertFalse(cursos.isEmpty(), "Debe haber al menos un curso para realizar esta prueba");

        Curso curso = cursos.get(0);
        String titulo = curso.getTitulo();

        Curso encontrado = repo.buscarCursoPorNombre(titulo);
        assertNotNull(encontrado, "El curso buscado por nombre debería existir");
        assertEquals(titulo, encontrado.getTitulo(), "El título del curso encontrado debería coincidir");
    }
    
    @Test
    void testBuscarCursoPorNombreInexistente() {
        String nombreInexistente = "Curso que no existe";
        Curso resultado = repo.buscarCursoPorNombre(nombreInexistente);
        assertNull(resultado, "El método debería devolver null si el curso no existe");
    }
    
    @Test
    void testAnadirCursoConArchivoTemporal() throws Exception {
        // Crear archivo temporal con contenido JSON de un curso
        File tempFile = File.createTempFile("comprobarAnadirCurso", ".json");
        tempFile.deleteOnExit(); // se borra al salir del programa

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("""
            		     {
						    "titulo": "Importar curso",
						    "descripcion": "Este es un ejemplo para importar un curso",
						    "rutaImagen": "epa",
						    "bloquesContenidos": [
						      {
							        "titulo": "El bloque",
							        "descripcion": "EL mejor bloque del mejor curso.",
							        "rutaImagen": "pipo",
							        "tareas": [
						          	  {
							            "tipo": "Tip",
							            "enunciado": null,
							            "imagen": "soyese.png"
						          	  }
						        	]
						      }
						    ]
						}
            """);
        }

        // Asegurarnos de que aún no existe en el repositorio
        assertNull(repo.buscarCursoPorNombre("Importar curso"));

        // Añadir curso usando ese archivo temporal
        repo.anadirCurso(tempFile.getAbsolutePath());

        // Verificar que ahora existe
        Curso curso = repo.buscarCursoPorNombre("Importar curso");
        assertNotNull(curso);
        assertEquals("Importar curso", curso.getTitulo());
    }
    
    @Test
    void testBuscarBloquePorNombreExistente() {
        // Utilizamos "Primera Guerra Púnica (264-241 a.C.)" que es el primer bloque de guerrasPunicas.json
        Optional<BloqueContenidos> bloqueOpt = repo.buscarBloquePorNombre("Primera Guerra Púnica (264-241 a.C.)");

        assertTrue(bloqueOpt.isPresent(), "Debería encontrarse un bloque con nombre 'Primera Guerra Púnica (264-241 a.C.)'");
        assertEquals("Primera Guerra Púnica (264-241 a.C.)", bloqueOpt.get().getTitulo(), "El nombre del bloque encontrado debería coincidir");
    }
    
    @Test
    void testBuscarBloquePorNombreInexistente() {
        Optional<BloqueContenidos> bloqueOpt = repo.buscarBloquePorNombre("elbloquefantasma");

        assertTrue(bloqueOpt.isEmpty(), "No debería encontrarse un bloque con nombre 'elbloquefantasma'");
    }



}
