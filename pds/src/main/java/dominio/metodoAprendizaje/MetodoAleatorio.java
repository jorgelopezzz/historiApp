package dominio.metodoAprendizaje;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dominio.tarea.Tarea;

public class MetodoAleatorio implements MetodoAprendizajeEstrategia {
	@Override
	public List<Tarea> generarTareas(List<Tarea> tareas) {
		List<Tarea> copia = new ArrayList<>(tareas);
		Collections.shuffle(copia);
		return copia;
	}
}
