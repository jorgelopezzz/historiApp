package dominio.metodoAprendizaje;

import java.util.List;

import dominio.tarea.Tarea;

public class MetodoEspaciado implements MetodoAprendizajeEstrategia {
	@Override
	public List<Tarea> generarTareas(List<Tarea> tareas) {
		return tareas;		
	}
}
