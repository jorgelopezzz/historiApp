package dominio;

import java.util.List;
import java.util.Optional;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;

public class IteradorAleatoria extends IteradorTarea {
	
    public IteradorAleatoria(List<Tarea> tareas) {
    	super(tareas);
        Collections.shuffle(this.tareas, new Random());
    }

    @Override
    public Optional<Tarea> siguiente(Optional<String> respuesta) {
    	if(respuesta.isPresent()) {
    		Pregunta preguntaActual = (Pregunta) tareas.get(indice);
    		preguntasTotales += 1;
    		preguntasCorrectas += preguntaActual.evaluar(respuesta.get()) ? 1 : 0;
    	}
    	
        if (!tieneSiguiente()) {
            return Optional.empty();
        }
        
        return Optional.of(tareas.get(++indice));
    }
}
