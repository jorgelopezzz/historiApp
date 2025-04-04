package dominio.metodoAprendizaje;

import java.util.List;
import java.util.Optional;

import dominio.tarea.Tarea;

public class IteradorEspaciado extends IteradorTarea {
	
	private static int ESPACIADO = 3;
	
    private boolean repetir;

    public IteradorEspaciado(List<Tarea> tareas) {
        super(tareas);
        this.repetir = true;
    }

    @Override
    public Tarea siguiente(Optional<String> respuesta) {
        
        Tarea tareaActual = tareas.get(indice);
        
        preguntasTotales += 1;
		preguntasCorrectas += tareaActual.evaluar(respuesta) ? 1 : 0;
       
        if ((indice+1) % ESPACIADO == 0) {
            if (repetir) {
            	indice -= ESPACIADO;
                repetir = false;
            } else {
                repetir = true;
            }
        }
             
        if (!tieneSiguiente())
            return null;
       
        return tareas.get(++indice);
    }
}
