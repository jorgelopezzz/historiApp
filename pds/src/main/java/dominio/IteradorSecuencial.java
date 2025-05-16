package dominio;

import java.util.List;
import java.util.Optional;

public class IteradorSecuencial extends IteradorTarea {
	
	public IteradorSecuencial(List<Tarea> tareas) {
		super(tareas);
	}
	
	@Override
	public Optional<Tarea> siguiente(Optional<String> respuesta) {
		if(respuesta.isPresent()) {
			Pregunta preguntaActual = (Pregunta) tareas.get(indice);
			preguntasTotales += 1;
			preguntasCorrectas += preguntaActual.evaluar(respuesta.get()) ? 1 : 0;
		}

		if(!tieneSiguiente())
			return Optional.empty();
		
		return Optional.of(tareas.get(++indice));
	}
}
