package gui.campos;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

import gui.GestorGUI;

public abstract class CampoPredeterminado {
	private JPanel panel;
	private JLabel etiqueta;
	protected JTextComponent campo;
	private static final int ANCHO_PREDET = 70;
	private static final int ALTO_PREDET = 30;
	
	public CampoPredeterminado(String texto, int ancho, int alto) {
		
		/* Configuración del panel */
		panel = new JPanel();
		GestorGUI.configurarPanel(panel, false, new FlowLayout());
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
	
	protected abstract boolean campoValido();
	
	public boolean comprobarCampo() {
		if(!campoValido()) {
			campo.setBackground(GestorGUI.getInstancia().getColorError());
			return false;
		}
		campo.setBackground(GestorGUI.getInstancia().getColorBlanco());
		return true;
		
	}
	
	public String getTexto() {
		return campo.getText();
	}
	
}
