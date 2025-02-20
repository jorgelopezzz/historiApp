package gui.campos;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

import gui.GestorGUI;

public abstract class CampoPredeterminado {
	private JPanel panel;
	private JLabel etiqueta;
	protected JTextComponent campo;
	
	public CampoPredeterminado(String texto, int ancho, int alto) {
		
		/* Configuración del panel */
		panel = new JPanel();
		GestorGUI.configurarPanel(panel, false, new FlowLayout());
		panel.setAlignmentX(FlowLayout.LEFT);
		
		/* Configuración de la etiqueta */
		etiqueta = new JLabel(texto);
		GestorGUI.configurarEtiqueta(etiqueta, false, GestorGUI.getInstancia().getColorOscuro(),
				GestorGUI.getInstancia().getFuenteTexto());
		
		/* Configuración del campo (en subclases) */
		construirCampo(ancho, alto);
		campo.setBorder(new LineBorder(GestorGUI.getInstancia().getColorOscuro()));
		GestorGUI.fijarTamano(ancho, alto, campo);
		campo.setBackground(GestorGUI.getInstancia().getColorBlanco());
		
		/* Montaje */
		panel.add(etiqueta);
		panel.add(campo);
		
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
