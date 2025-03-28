package dominio.metodoAprendizaje;

import java.util.List;

import dominio.tarea.Tarea;

public class GestorEstrategiaAprendizaje {
	private MetodoAprendizajeEstrategia estrategia;
	
	public GestorEstrategiaAprendizaje(MetodoAprendizaje metodoEstrategia) {
		switch(metodoEstrategia) {
			case SECUENCIAL:
				this.estrategia = new MetodoSecuencial();
				break;
			case ALEATORIA:
				this.estrategia = new MetodoAleatorio();
				break;
			case ESPACIADA:
				this.estrategia = new MetodoEspaciado();
				break;
			default:
				throw new IllegalArgumentException("Tipo de estrategia no soportado");
		}
	}

	public List<Tarea> generarTareas(List<Tarea> tareas) {
		return estrategia.generarTareas(tareas);
	}
}
