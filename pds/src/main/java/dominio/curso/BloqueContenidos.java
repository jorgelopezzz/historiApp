package dominio.curso;

import java.util.List;

import dominio.tarea.Tarea;

public class BloqueContenidos {
    private String titulo;
    private String descripcion;
    private String rutaImagen;
    private CertificadoBloque certBloque;
    private List<Tarea> tareas;
    
    public BloqueContenidos() {} // Constructor vacío para Jackson (servicioJSON)
    
    public BloqueContenidos(String titulo, String descripcion, String rutaImagen, CertificadoBloque certBloque, List<Tarea> tareas) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        this.certBloque = certBloque;
        this.tareas = tareas;
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
