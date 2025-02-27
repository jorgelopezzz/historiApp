package gui.componentes;

import javax.swing.JLabel;

import gui.GestorGUI;
import gui.info.InfoBloque;
import gui.info.InfoCurso;

public class ComponenteBloque extends Componente {

	/* Cadenas estándar */
	private static final String CABECERA_TITULO = "Bloque de contenidos:"; 
	
	public ComponenteBloque(InfoBloque info) {
		super(CABECERA_TITULO, info.getTitulo(), info.getDescripcion(), info.getRutaImagen(), 
				info.estaCompletado() ? "Completado" : "No completado");
	}

}
