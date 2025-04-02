package dominio.metodoAprendizaje;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;
import dominio.tarea.Tarea;

public class IteradorAleatoria implements IteradorTarea {
    private List<Tarea> tareas;
    private int indice;

    public IteradorAleatoria(List<Tarea> tareas) {
        this.tareas = new ArrayList<>(tareas);
        Collections.shuffle(this.tareas, new Random());
        this.indice = 0;
    }

    @Override
    public boolean tieneSiguiente() {
        return indice < tareas.size();
    }

    @Override
    public Tarea siguiente() {
        if (!tieneSiguiente()) {
            return null;
        }
        return tareas.get(indice++);
    }
}
