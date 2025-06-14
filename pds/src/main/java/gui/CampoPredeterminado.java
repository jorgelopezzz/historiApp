package gui;

import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import utils.GestorGUI;

public abstract class CampoPredeterminado {
	private JPanel panel;
	private JLabel etiqueta;
	protected JComponent campo;
	private static final int ANCHO_PREDET = 70;
	private static final int ALTO_PREDET = 30;
	
	protected CampoPredeterminado(String texto, int ancho, int alto) {
		
		/* Configuración del panel */
		panel = new JPanel();
		GestorGUI.configurarPanel(panel, new FlowLayout(), false);
		panel.setAlignmentX(FlowLayout.LEFT);
		
		/* Configuración de la etiqueta */
		etiqueta = new JLabel(texto);
		GestorGUI.configurarEtiqueta(etiqueta, false, GestorGUI.getInstancia().getColorOscuro(),
				GestorGUI.getInstancia().getFuenteTexto());
		GestorGUI.fijarTamano(ANCHO_PREDET, ALTO_PREDET, etiqueta);
		etiqueta.setHorizontalAlignment(SwingConstants.RIGHT);
		
		/* Configuración del campo (en subclases) */
		construirCampo(ancho, alto);
		campo.setBorder(new LineBorder(GestorGUI.getInstancia().getColorOscuro()));
		GestorGUI.fijarTamano(ancho, alto, campo);
		campo.setBackground(GestorGUI.getInstancia().getColorBlanco());
		
		/* Montaje */
		panel.add(etiqueta);
		panel.add(campo);
		
	}
	
	public CampoPredeterminado(String texto, int ancho, int alto, int tamanoEtiqueta) {
		this(texto, ancho, alto);
		GestorGUI.fijarTamano(tamanoEtiqueta, ALTO_PREDET, etiqueta);
	}
	
	protected abstract void construirCampo(int ancho, int alto);
	
	public JPanel getPanel() {
		return panel;
	}
	
	public boolean campoValido() {
		return ! getTexto().isEmpty();
	}
	
	public boolean comprobarCampo() {
		if(!campoValido()) {
			campo.setBackground(GestorGUI.getInstancia().getColorError());
			return false;
		}
		campo.setBackground(GestorGUI.getInstancia().getColorBlanco());
		return true;
		
	}
	
	public abstract String getTexto();
	
}
