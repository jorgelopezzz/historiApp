package modelo;

import java.util.ArrayList;
import java.util.List;

public class BloqueContenidos {
    private String titulo;
    private String descripcion;
    private String rutaImagen;
    private List<Tarea> tareas;
    
    public BloqueContenidos() {} // Constructor vacío para Jackson (servicioJSON)
    
    public BloqueContenidos(String titulo, String descripcion, String rutaImagen, List<Tarea> tareas) {
        if(titulo == null)
        	throw new IllegalArgumentException("El título no puede ser nulo");
    	this.titulo = titulo;
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        this.tareas = tareas != null ? new ArrayList<>(tareas) : new ArrayList<Tarea>();
    }

    public String getTitulo() {
        return titulo;
    }
    
    public String getDescripcion() {
    	return descripcion;
    }
    
    public String getRutaImagen() {
    	return rutaImagen;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }
}
