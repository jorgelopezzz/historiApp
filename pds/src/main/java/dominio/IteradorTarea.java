package dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class IteradorTarea {
	protected List<Tarea> tareas;
	protected int indice;
	protected int preguntasTotales;
	protected int preguntasCorrectas;

	protected IteradorTarea(List<Tarea> tareas) {
		this.tareas = new ArrayList<>(tareas);
		this.indice = -1;
        this.preguntasTotales = 0;
        this.preguntasCorrectas = 0;
	}
	
	public boolean tieneSiguiente() {
		return indice < tareas.size()-1;
	}
	
	public abstract Optional<Tarea> siguiente(Optional<String> respuesta);
	
	public double obtenerPuntuacion() {
		return preguntasTotales == 0.0 ? 10.0 : preguntasCorrectas*10/preguntasTotales;
	}
	
}
