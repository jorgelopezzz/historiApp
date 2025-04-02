package gui.componentes.tarea;

import java.util.Optional;

import dominio.info.Info;

public abstract class ComponentePregunta extends ComponenteTarea {

	/* Cadenas por defecto */
	protected static final String CABECERA = "Enunciado:";
	
	public ComponentePregunta(Info info) {
		super(info);
	}

	public abstract Optional<String> getRespuesta();
	
}
