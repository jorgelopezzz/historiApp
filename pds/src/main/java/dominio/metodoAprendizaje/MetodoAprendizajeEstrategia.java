package dominio.metodoAprendizaje;

import java.util.List;

import dominio.tarea.Tarea;

public interface MetodoAprendizajeEstrategia {
	public List<Tarea> generarTareas(List<Tarea> tareas);
}
