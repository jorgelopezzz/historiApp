package gui.componentes;

import gui.info.InfoCurso;

@SuppressWarnings("serial")
public class ComponenteTarea extends Componente {
	/* Cadenas estándar */
	private static final String CABECERA_TITULO = "Tarea:"; 
	
	public ComponenteTarea(InfoCurso info) {
		super(CABECERA_TITULO, info.getTitulo(), info.getDescripcion(), info.getRutaImagen(), 
				info.estaMatriculado() ? "Matriculado" : "No matriculado");
	}
}
