package repositorios;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import modelo.BloqueContenidos;
import modelo.Curso;

public enum RepositorioCursos {
    INSTANCE;

    private Map<String, Curso> cursosPorNombre = new HashMap<>();
    private ServicioCursoLoader loader;

    public void init(ServicioCursoLoader loader) {
        if (this.loader != null) {
           System.err.println("El RepositorioCursos ya ha sido inicializado");
           return;
        }

        this.loader = loader;
        cargarCursos();
    }
    
    private void cargarCursos() {    
    	File carpeta = new File("resources/cursos");
        if(!carpeta.exists() || !carpeta.isDirectory()) {
        	System.err.println("La carpeta 'resources' no existe o no es un directorio válido");
            cursosPorNombre = new HashMap<String, Curso>();
            return;
        }
        	
    	cursosPorNombre = Arrays.stream(carpeta.listFiles((dir, name) -> name.endsWith(".json")))
                    .map(File::getPath)
                    .map(ruta -> {
                        try {
                            return loader.cargarCurso(ruta);
                        } catch (Exception e) {
                            System.err.println("Error al cargar el curso desde " + ruta + ": " + e.getMessage());
                            return null;
                        }
                    })
                    .filter(curso -> curso != null)
                    .collect(Collectors.toMap(Curso::getTitulo, curso -> curso));
    }
    
    public void anadirCurso(String ruta) {
    	Curso nuevoCurso;
    	try {
			 nuevoCurso = loader.cargarCurso(ruta);
		} catch (IOException e) {
			System.err.println("Error al cargar el curso desde " + ruta + ": " + e.getMessage());
            return;
		}
    	cursosPorNombre.put(nuevoCurso.getTitulo(), nuevoCurso);
    }

    public List<Curso> getCursos() {
        return new ArrayList<>(cursosPorNombre.values());
    }
    
    public Curso buscarCursoPorNombre(String nombreCurso) {
		return cursosPorNombre.get(nombreCurso);
	}
    
    public Optional<BloqueContenidos> buscarBloquePorNombre(String nombreBloque) {
		return cursosPorNombre.values().stream()
				.map(curso -> curso.getBloquePorNombre(nombreBloque))
				.filter(Objects::nonNull)
				.findFirst();
	}
    
    public List<BloqueContenidos> getBloques(Curso curso) {
    	return curso.getBloquesContenidos();
    }
}

