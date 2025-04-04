package dominio.curso;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dominio.ServicioJSON;
import dominio.usuario.Usuario;

public enum RepositorioCursos {
    INSTANCE;

    private Map<String, Curso> cursosPorNombre;

    private RepositorioCursos() {  	
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
                            return ServicioJSON.INSTANCE.cargarCurso(ruta);
                        } catch (Exception e) {
                            System.err.println("Error al cargar el curso desde " + ruta + ": " + e.getMessage());
                            return null;
                        }
                    })
                    .filter(curso -> curso != null)
                    .collect(Collectors.toMap(Curso::getTitulo, curso -> curso));
    	
    }

    public List<Curso> getCursos() {
        return new ArrayList<>(cursosPorNombre.values());
    }
    
    public Curso buscarCursoPorNombre(String nombreCurso) {
		return cursosPorNombre.get(nombreCurso);
	}
    
    public List<BloqueContenidos> getBloques(Curso curso) {
    	return curso.getBloquesContenidos();
    }
}

