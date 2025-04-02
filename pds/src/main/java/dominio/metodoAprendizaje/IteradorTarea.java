package dominio.metodoAprendizaje;

import dominio.tarea.Tarea;

public interface IteradorTarea {
	boolean tieneSiguiente();
	Tarea siguiente();
}
