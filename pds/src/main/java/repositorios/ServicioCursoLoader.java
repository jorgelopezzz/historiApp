package repositorios;

import java.io.IOException;

import modelo.Curso;

public interface ServicioCursoLoader {
    Curso cargarCurso(String ruta) throws IOException;
}
