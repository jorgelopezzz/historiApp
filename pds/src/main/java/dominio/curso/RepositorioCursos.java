package dominio.curso;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import dominio.ServicioJSON;

public enum RepositorioCursos {
    INSTANCE;

    private List<Curso> cursos;

    private RepositorioCursos() {
    	File carpeta = new File("resources");
        cursos = Arrays.stream(carpeta.listFiles((dir, name) -> name.endsWith(".json")))
                    .map(File::getPath)
                    .map(ruta -> {
                        try {
                            return ServicioJSON.cargarCurso(ruta);
                        } catch (Exception e) {
                            System.err.println("Error al cargar el curso desde " + ruta + ": " + e.getMessage());
                            return null;
                        }
                    })
                    .filter(curso -> curso != null)
                    .collect(Collectors.toList());
        
        for(Curso curso: cursos) {
        	System.out.println(curso.getNombre());
        }
        System.out.println("hola");
    }

    public List<Curso> getCursos() {
        return cursos;
    }
}

