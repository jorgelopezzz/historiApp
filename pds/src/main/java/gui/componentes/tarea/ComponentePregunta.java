package gui.componentes.tarea;

import gui.info.Info;

public abstract class ComponentePregunta extends ComponenteTarea {

	/* Cadenas por defecto */
	protected static final String CABECERA = "Enunciado:";
	
	public ComponentePregunta(Info info) {
		super(info);
	}

	public abstract boolean evaluar();
}
