package dominio.metodoAprendizaje;

import java.util.List;
import dominio.tarea.Tarea;

public class IteradorEspaciado implements IteradorTarea {
	
	private static int ESPACIADO = 3;
	
    private List<Tarea> tareas;
    private int indice;
    private boolean repetir;

    public IteradorEspaciado(List<Tarea> tareas) {
        this.tareas = tareas;
        this.indice = 0;
        this.repetir = true;
    }

    @Override
    public boolean tieneSiguiente() {
        return indice < tareas.size();
    }

    @Override
    public Tarea siguiente() {
        if (!tieneSiguiente())
            return null;

        Tarea tarea = tareas.get(indice);
       
        if ((indice+1) % ESPACIADO == 0) {
            if (repetir) {
            	indice -= ESPACIADO;
                repetir = false;
            } else {
                repetir = true;
            }
        }
       
        indice++;
        
        return tarea;
    }
}
