package gui.componentes;

import gui.info.InfoBloque;

@SuppressWarnings("serial")
public class ComponenteBloque extends Componente {

	/* Cadenas estándar */
	private static final String CABECERA_TITULO = "Bloque de contenidos:"; 
	
	public ComponenteBloque(InfoBloque info) {
		super(CABECERA_TITULO, info.getTitulo(), info.getDescripcion(), info.getRutaImagen(), 
				info.estaCompletado() ? "Completado" : "No completado");
	}

}
