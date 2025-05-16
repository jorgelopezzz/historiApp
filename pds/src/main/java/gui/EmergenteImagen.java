package gui;

import java.awt.Component;
import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utils.GestorGUI;

public class EmergenteImagen extends EmergenteFichero {

	/* Vista previa */
	private JLabel etiquetaImagen;
	
	/* Dimensiones */
	private static final int LADO_IMAGEN = 150;
	
	public EmergenteImagen(JFrame ventanaMadre) {
		super(ventanaMadre, "Imagen");
	}
	
	@Override
	protected void manejadorPanelFichero() {
		rutaFichero = panelFichero.getSelectedFile().getAbsolutePath();
        establecerImagen(rutaFichero);
	}
	
	@Override
	protected void manejadorPanelArrastre(List<File> ficheros) {
		rutaFichero = ficheros.get(0).getAbsolutePath();
		establecerImagen(rutaFichero);
	}
	
	@Override
	protected void construirpanelInferior(String ruta) {
		
		/* Configuración panel */
		panelInferior = new JPanel();
		GestorGUI.configurarPanel(panelInferior, new BoxLayout(panelInferior, BoxLayout.X_AXIS), false);
		
		/* Vista previa */
		etiquetaImagen = new JLabel();
		etiquetaImagen.setIcon(GestorGUI.getInstancia().iconoDeRecursos(GestorGUI.IMAGEN_PREDET_OSC, LADO_IMAGEN, LADO_IMAGEN));
		etiquetaImagen.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		/* Montaje */
		panelInferior.add(Box.createHorizontalStrut( (int) ((ANCHO_EMERGENTE_PREDET - GestorGUI.ANCHO_BOTON_PREDET)/ 4.0) ));
		panelInferior.add(etiquetaImagen);
		montarBotones();

		
	}
	
	private void establecerImagen(String ruta) {
		// Restaurar la etiqueta a su estado original
	    etiquetaImagen.setIcon(null);
	    etiquetaImagen.setText("");
	    etiquetaImagen.setOpaque(false);
	    etiquetaImagen.setBorder(null);
	 
		ImageIcon icono = GestorGUI.getInstancia().iconoAbsoluto(ruta, LADO_IMAGEN, LADO_IMAGEN);
	    if (icono != null && icono.getIconWidth() > 0) {
	        etiquetaImagen.setIcon(icono);
	    } else {
	    	etiquetaImagen.setIcon(null);
	    	etiquetaImagen.setText(new File(ruta).getName());
	        etiquetaImagen.setHorizontalAlignment(SwingConstants.CENTER);
	        etiquetaImagen.setVerticalAlignment(SwingConstants.CENTER);
	        etiquetaImagen.setForeground(GestorGUI.getInstancia().getColorBlanco());
	        etiquetaImagen.setOpaque(true);
	        etiquetaImagen.setBackground(GestorGUI.getInstancia().getColorOscuro());
	        etiquetaImagen.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createLineBorder(GestorGUI.getInstancia().getColorOscuro(), 2),
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)
	        ));
	    }
	}
	
}
