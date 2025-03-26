package gui.componentes.contenidos;

import gui.info.contenidos.InfoBloque;

@SuppressWarnings("serial")
public class ComponenteBloque extends ComponenteContenido {

	/* Cadenas estándar */
	private static final String CABECERA_TITULO = "Bloque de contenidos:"; 
	
	public ComponenteBloque(InfoBloque info) {
		super(CABECERA_TITULO, info.getTitulo(), info.getDescripcion(), info.getRutaImagen(), 
				info.estaCompletado() ? "Completado" : "No completado");
	}

}
