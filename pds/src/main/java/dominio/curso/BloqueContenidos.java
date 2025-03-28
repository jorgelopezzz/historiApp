package dominio.curso;

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
    
    public BloqueContenidos(String titulo, String descripcion, String rutaImagen, CertificadoBloque certBloque, MetodoAprendizaje metodoAprendizaje, List<Tarea> tareas) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        this.certBloque = null;
        this.metodoAprendizaje = metodoAprendizaje;
        this.tareas = tareas;
        GestorEstrategiaAprendizaje gestor = new GestorEstrategiaAprendizaje(metodoAprendizaje);
        tareasMetodoAprendizaje = gestor.generarTareas(tareas);
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
    
    /* Patrón iterador */
    
    public boolean tieneSiguiente() {
        return posicion < tareasMetodoAprendizaje.size();
    }


    public Tarea siguiente() {
        if (!tieneSiguiente()) {
        	String usuarioActual = "usuario"; //llamar controlador
            certBloque = new CertificadoBloque(LocalDate.now(), usuarioActual, this);
        }
        return tareasMetodoAprendizaje.get(posicion++);
    }
    
    /*////////////////*/

}
