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
        if(!carpeta.exists() || !carpeta.isDirectory()) {
        	System.err.println("La carpeta 'resources' no existe o no es un directorio válido");
            cursos = List.of();
        }
        	
    	cursos = Arrays.stream(carpeta.listFiles((dir, name) -> name.endsWith(".json")))
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
                    .collect(Collectors.toList());
        
        ///
        for(Curso curso: cursos) {
        	System.out.println("\n"+curso.getTitulo()
        			+","+curso.getDescripcion()
        			+","+curso.getRutaImagen()
        			+",");
        	curso.getBloquesContenidos().stream().forEach(b -> System.out.print(b.getTitulo()+","));
        }
        ///
    }

    public List<Curso> getCursos() {
        return cursos;
    }
    
    public List<BloqueContenidos> getBloques(Curso curso) {
    	return curso.getBloquesContenidos();
    }
}

