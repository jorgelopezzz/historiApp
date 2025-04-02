package dominio.curso;

import java.util.ArrayList;
import java.util.List;

import dominio.tarea.Tarea;

public class BloqueContenidos {
    private String titulo;
    private String descripcion;
    private String rutaImagen;
    private CertificadoBloque certBloque;
    private List<Tarea> tareas;
    
    public BloqueContenidos() {} // Constructor vacío para Jackson (servicioJSON)
    
    public BloqueContenidos(String titulo, String descripcion, String rutaImagen, List<Tarea> tareas) {
        if(titulo == null)
        	throw new IllegalArgumentException("El título no puede ser nulo");
    	this.titulo = titulo;
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        this.certBloque = null;
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
    
    public CertificadoBloque getCertBloque() {
    	return certBloque;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }
}
