package dominio.curso;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

import dominio.metodoAprendizaje.GestorEstrategiaAprendizaje;
import dominio.metodoAprendizaje.MetodoAprendizaje;
import dominio.tarea.Tarea;
import dominio.usuario.Usuario;

public class BloqueContenidos {
    private String titulo;
    private String descripcion;
    private String rutaImagen;
    private CertificadoBloque certBloque;
    private MetodoAprendizaje metodoAprendizaje;
    private List<Tarea> tareas;
    private List<Tarea> tareasMetodoAprendizaje;
    private int posicion;
    
    public BloqueContenidos() {} // Constructor vacío para Jackson (servicioJSON)
    
    public BloqueContenidos(String titulo, String descripcion, String rutaImagen, MetodoAprendizaje metodoAprendizaje, List<Tarea> tareas) {
        if(titulo == null)
        	throw new IllegalArgumentException("El título no puede ser nulo");
    	this.titulo = titulo;
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        this.certBloque = null;
        this.tareas = tareas != null ? new ArrayList<>(tareas) : new ArrayList<Tarea>();
        this.metodoAprendizaje = metodoAprendizaje == null ? MetodoAprendizaje.SECUENCIAL : metodoAprendizaje;
        GestorEstrategiaAprendizaje gestor = new GestorEstrategiaAprendizaje(this.metodoAprendizaje);
        tareasMetodoAprendizaje = gestor.generarTareas(this.tareas);
        posicion = 0;
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
    
    public void setMetodoAprendizaje(MetodoAprendizaje metodoAprendizaje) {
    	this.metodoAprendizaje = metodoAprendizaje == null ? MetodoAprendizaje.SECUENCIAL : metodoAprendizaje;
        GestorEstrategiaAprendizaje gestor = new GestorEstrategiaAprendizaje(this.metodoAprendizaje);
        tareasMetodoAprendizaje = gestor.generarTareas(this.tareas);
    }
    
    /* Patrón iterador */
    
    public boolean tieneSiguiente() {
        return posicion < tareasMetodoAprendizaje.size();
    }


    public Tarea siguiente() {
        if (tieneSiguiente())
        	return tareasMetodoAprendizaje.get(posicion++);

        String usuarioActual = "usuario"; //llamar controlador
        certBloque = new CertificadoBloque(LocalDate.now(), usuarioActual, this);
        return null;
    }
    
    /*////////////////*/

}
