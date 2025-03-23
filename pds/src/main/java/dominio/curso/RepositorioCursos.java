package dominio.curso;

import java.util.ArrayList;
import java.util.List;

import dominio.ServicioJSON;

public enum RepositorioCursos {
	INSTANCE;

    private static List<Curso> cursos = new ArrayList<>();

    public static void inicializar(String rutaArchivo) {
        try {
            cursos.add(ServicioJSON.cargarCurso(rutaArchivo));
        } catch (Exception e) {
            System.err.println("Error al cargar el curso: " + e.getMessage());
        }
    }

    public static List<Curso> getCursos() {
        return cursos;
    }
}
