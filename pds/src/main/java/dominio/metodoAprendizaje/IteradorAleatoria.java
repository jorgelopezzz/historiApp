package dominio.metodoAprendizaje;

import java.util.List;
import java.util.Optional;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;
import dominio.tarea.Tarea;

public class IteradorAleatoria extends IteradorTarea {
	
    public IteradorAleatoria(List<Tarea> tareas) {
    	super(tareas);
        Collections.shuffle(this.tareas, new Random());
    }

    @Override
    public Tarea siguiente(Optional<String> respuesta) {
    	Tarea tareaActual = tareas.get(indice);
    	
    	if(respuesta.isPresent()) {
    		preguntasTotales += 1;
    		preguntasCorrectas += tareaActual.evaluar(respuesta) ? 1 : 0;
    	}
    	
        if (!tieneSiguiente()) {
            return null;
        }
        
        return tareas.get(++indice);
    }
}
