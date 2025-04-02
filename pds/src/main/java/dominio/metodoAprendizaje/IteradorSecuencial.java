package dominio.metodoAprendizaje;

import java.util.List;

import dominio.tarea.Tarea;

public class IteradorSecuencial implements IteradorTarea {
	private List<Tarea> tareas;
	private int indice;
	
	public IteradorSecuencial(List<Tarea> tareas) {
		this.tareas = tareas;
		indice = 0;
	}
	
	@Override
	public boolean tieneSiguiente() {
		return indice < tareas.size();
	}
	
	@Override
	public Tarea siguiente() {
		if(!tieneSiguiente())
			return null;
		Tarea tarea = tareas.get(indice);
		indice += 1;
		return tarea;
	}
}
