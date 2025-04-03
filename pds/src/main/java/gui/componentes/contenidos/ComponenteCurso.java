package gui.componentes.contenidos;

import dominio.info.contenidos.InfoCurso;

@SuppressWarnings("serial")
public class ComponenteCurso extends ComponenteContenido {
	
	/* Cadenas estándar */
	private static final String CABECERA_TITULO = "Curso:"; 
	
	public ComponenteCurso(InfoCurso info) {
		super(CABECERA_TITULO, info.getTitulo(), info.getDescripcion(), info.getRutaImagen(), 
				info.estaMatriculado() ? "Matriculado" : "No matriculado");
	}
}
