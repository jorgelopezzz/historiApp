package dominio.curso;

import java.util.List;

import dominio.tarea.Tarea;

public class BloqueContenidos {
    private String nombre;
    private List<Tarea> tareas;

    public BloqueContenidos(String nombre, List<Tarea> tareas) {
        this.nombre = nombre;
        this.tareas = tareas;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }
}
