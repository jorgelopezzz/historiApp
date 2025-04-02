package dominio.metodoAprendizaje;

import java.util.List;
import dominio.tarea.Tarea;

public class IteradorEspaciado implements IteradorTarea {
    private List<Tarea> tareas;
    private int indice;
    private int subindice;
    private boolean repitiendo;

    public IteradorEspaciado(List<Tarea> tareas) {
        this.tareas = tareas;
        this.indice = 0;
        this.subindice = 0;
        this.repitiendo = false;
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
        //revisar
        if (repitiendo) {
            Tarea tarea = tareas.get(indice - 5 + subindice);
            subindice++;

            if (subindice == 5) {
                repitiendo = false;
                subindice = 0;
            }
            return tarea;
        } else {
            Tarea tarea = tareas.get(indice);
            indice++;

            if (indice % 5 == 0 && indice < tareas.size()) {
                repitiendo = true;
                subindice = 0;
            }
            return tarea;
        }
    }
}
