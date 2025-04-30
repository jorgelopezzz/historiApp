package repositorios;

import dominio.curso.Curso;
import java.io.IOException;

public interface ServicioCursoLoader {
    Curso cargarCurso(String ruta) throws IOException;
}
