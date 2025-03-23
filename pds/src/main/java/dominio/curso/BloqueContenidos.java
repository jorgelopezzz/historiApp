package dominio.curso;

import java.util.List;

import dominio.tarea.Tarea;

public class BloqueContenidos {
    private String nombre;
    private CertificadoBloque certBloque;
    private List<Tarea> tareas;

    public BloqueContenidos(String nombre, CertificadoBloque certBloque, List<Tarea> tareas) {
        this.nombre = nombre;
        this.certBloque = certBloque;
        this.tareas = tareas;
    }

    public String getNombre() {
        return nombre;
    }
    
    public CertificadoBloque getCertBloque() {
    	return certBloque;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }
}
