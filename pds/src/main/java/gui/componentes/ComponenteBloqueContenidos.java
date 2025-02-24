package gui.componentes;

import javax.swing.JLabel;

import gui.GestorGUI;
import gui.info.InfoBloqueContenidos;
import gui.info.InfoCurso;

public class ComponenteBloqueContenidos extends Componente {

	/* Cadenas estándar */
	private static final String CABECERA_TITULO = "Bloque de contenidos:"; 
	
	public ComponenteBloqueContenidos(InfoBloqueContenidos info) {
		super(CABECERA_TITULO, info.getTitulo(), info.getDescripcion(), info.getRutaImagen(), 
				info.estaCompletado() ? "Completado" : "No completado");
	}

}
