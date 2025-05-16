package repositorios;

import java.io.IOException;

import dominio.Curso;

public interface ServicioCursoLoader {
    Curso cargarCurso(String ruta) throws IOException;
}
