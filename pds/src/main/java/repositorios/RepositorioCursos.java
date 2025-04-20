package repositorios;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import dominio.ServicioJSON;
import dominio.curso.BloqueContenidos;
import dominio.curso.Curso;

public enum RepositorioCursos {
    INSTANCE;

    private Map<String, Curso> cursosPorNombre;

    private RepositorioCursos() {  	
<<<<<<< HEAD
    	URL recurso = getClass().getClassLoader().getResource("cursos");
    	
        if (recurso == null) {
            System.out.println("No se encontró la carpeta.");
            return;
        }
        
    	File carpeta = new File(recurso.getFile());
=======
    	File carpeta = new File("resources/cursos");
>>>>>>> branch 'main' of git@github.com:jorgelopezzz/historiApp.git
        if(!carpeta.exists() || !carpeta.isDirectory()) {
        	System.err.println("La carpeta 'resources' no existe o no es un directorio válido");
            cursosPorNombre = new HashMap<String, Curso>();
            return;
        }
        	
    	cursosPorNombre = Arrays.stream(carpeta.listFiles((dir, name) -> name.endsWith(".json")))
                    .map(File::getPath)
                    .map(ruta -> {
                        try {
                            return ServicioJSON.INSTANCE.cargarCurso(ruta);
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
			 nuevoCurso = ServicioJSON.INSTANCE.cargarCurso(ruta);
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

