package dominio.metodoAprendizaje;

import java.util.ArrayList;
import java.util.List;

import dominio.tarea.Tarea;

public class MetodoSecuencial implements MetodoAprendizajeEstrategia {
	@Override
	public List<Tarea> generarTareas(List<Tarea> tareas) {
		return new ArrayList<>(tareas);		
	}
}
