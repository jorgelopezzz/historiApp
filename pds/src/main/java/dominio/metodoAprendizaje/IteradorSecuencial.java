package dominio.metodoAprendizaje;

import java.util.List;
import java.util.Optional;

import dominio.tarea.Pregunta;
import dominio.tarea.Tarea;

public class IteradorSecuencial extends IteradorTarea {
	
	public IteradorSecuencial(List<Tarea> tareas) {
		super(tareas);
	}
	
	@Override
	public Tarea siguiente(Optional<String> respuesta) {
		System.out.println(indice);
		if(respuesta.isPresent()) {
			Pregunta preguntaActual = (Pregunta) tareas.get(indice);
			preguntasTotales += 1;
			preguntasCorrectas += preguntaActual.evaluar(respuesta.get()) ? 1 : 0;
		}

		if(!tieneSiguiente())
			return null;
		System.out.println("f:" + (indice+1));
		return tareas.get(++indice);
	}
}
