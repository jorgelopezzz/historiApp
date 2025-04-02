package gui.componentes.tarea;

import java.awt.Component;

import javax.swing.JLabel;

import dominio.info.tarea.InfoTip;
import gui.GestorGUI;

public class ComponenteTip extends ComponenteTarea {

	/* Cadenas por defecto */
	private static final String CABECERA = "Tip:";
	
	/* Componentes gráficos */
	private JLabel etiquetaImagen;
	
	public ComponenteTip(InfoTip info) {
		super(info);
		
		/* Muestra de imagen (si la hay) */
		info.getRutaImagen().ifPresent( ruta -> 
			{	etiquetaImagen = new JLabel();
				etiquetaImagen.setIcon(GestorGUI.getInstancia().iconoAbsoluto(ruta, ANCHO_IMAGEN, ALTO_IMAGEN));
				etiquetaImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
				add(etiquetaImagen);});
		
		/* Construcción de etiquetas */
		super.construir(CABECERA);
	}
	
		

}
