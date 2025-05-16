package gui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.InfoTip;
import utils.GestorGUI;

public class ComponenteTip extends ComponenteTarea {

	/* Cadenas por defecto */
	private static final String CABECERA = "Tip:";
	
	/* Componentes gráficos */
	private JLabel etiquetaImagen;
	
	public ComponenteTip(InfoTip info) {
		super(info, new BorderLayout());
		
		/* Muestra de imagen (si la hay) */
		info.getRutaImagen().ifPresent( ruta -> 
			{	etiquetaImagen = new JLabel();
				etiquetaImagen.setIcon(GestorGUI.getInstancia().iconoAbsoluto(ruta, ANCHO_IMAGEN, ALTO_IMAGEN));
				
				add(etiquetaImagen, BorderLayout.NORTH);});
		
		/* Construcción de etiquetas */
		construir(CABECERA);
	}
	
	@Override
	protected void construir(String cabecera) {
		/* Construcción */
		etiquetaCabecera = construirEtiquetaCabecera(cabecera);
		areaContenido = construirEtiquetaContenido(getEnunciado());
		
		/* Montaje */
		JPanel panelEtiquetas = new JPanel();
		GestorGUI.configurarPanel(panelEtiquetas, new BoxLayout(panelEtiquetas, BoxLayout.Y_AXIS), false);
		
		panelEtiquetas.add(etiquetaCabecera);
		panelEtiquetas.add(areaContenido);
		
		
		add(panelEtiquetas, BorderLayout.CENTER);
	
	}
	
	
		

}
